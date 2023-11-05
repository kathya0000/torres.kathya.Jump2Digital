package torres.kathya.Jump2Digital.services;

import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import torres.kathya.Jump2Digital.dto.SkinConverter;
import torres.kathya.Jump2Digital.dto.SkinDTO;
import torres.kathya.Jump2Digital.models.SkinModel;
import torres.kathya.Jump2Digital.models.UserSkin;
import torres.kathya.Jump2Digital.repositories.ISkinRepository;
import torres.kathya.Jump2Digital.repositories.UserSkinRepository;
import torres.kathya.Jump2Digital.security.repository.UserRepository;
import torres.kathya.Jump2Digital.security.user.User;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class SkinService {
    @Autowired
    private final ISkinRepository skinRepository;
    private final SkinConverter skinConverter;
    private final UserSkinRepository userSkinRepository;
    private final UserRepository userRepository;


    public SkinDTO addNewSkin(SkinDTO skinDTO) {
        SkinModel skinModel = skinConverter.dtoToModel(skinDTO);
        SkinModel savedSkin = skinRepository.save(skinModel);
        return skinConverter.modelToDto(savedSkin);
    }

    public List<SkinDTO> getSkinsByUserId(Long userId) {
        List<UserSkin> userSkins = userSkinRepository.findByUserId(userId);
        return userSkins.stream()
                .map(UserSkin::getSkin)
                .map(skinConverter::modelToDto) // Convertir a DTO
                .collect(Collectors.toList());
    }

    public List<SkinDTO> getSkins() {
        return skinRepository.findAll().stream()
                .map(skinConverter::modelToDto)
                .collect(Collectors.toList());
    }


    public SkinDTO getById(Long id) {
        SkinModel skin = skinRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Skin no encontrada con ID: " + id));
        return skinConverter.modelToDto(skin);
    }

    public SkinDTO updateSkinColor(Long id, String color) {
        SkinModel skin = skinRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Skin no encontrada con ID: " + id));
        skin.setColor(color);
        SkinModel updatedSkin = skinRepository.save(skin);
        return skinConverter.modelToDto(updatedSkin);
    }
    public boolean deleteSkin(Long id) {
        if (!skinRepository.existsById(id)) {
            throw new EntityNotFoundException("Skin no encontrada con ID: " + id);
        }

        // Encuentra todas las UserSkin que hacen referencia a esta Skin y elimínalas primero
        List<UserSkin> userSkins = userSkinRepository.findBySkinId(id);
        if (!userSkins.isEmpty()) {
            userSkinRepository.deleteAll(userSkins);
        }

        // Ahora que las referencias han sido eliminadas, puedes eliminar la Skin
        skinRepository.deleteById(id);
        return true;
    }

    public SkinDTO purchaseSkin(Long skinId) {
        // Obtener el usuario autenticado
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userEmail = authentication.getName();
        User user = userRepository.findByEmail(userEmail)
                .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado con email: " + userEmail));

        // Obtener la skin por ID
        SkinModel skin = skinRepository.findById(skinId)
                .orElseThrow(() -> new EntityNotFoundException("Skin no encontrada con ID: " + skinId));


        // Verificar si el usuario ya ha comprado la skin
        if (userSkinRepository.existsByUserAndSkin(user, skin)) {
            throw new IllegalStateException("El usuario ya ha comprado esta skin.");
        }

        // Crear y guardar la relación UserSkin
        UserSkin userSkin = new UserSkin();
        userSkin.setUser(user);
        userSkin.setSkin(skin);
        userSkinRepository.save(userSkin);

        // Devolver la SkinDTO
        return skinConverter.modelToDto(skin);
    }
}

