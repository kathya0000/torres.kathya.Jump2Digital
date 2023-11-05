package torres.kathya.Jump2Digital.dto;

import org.springframework.stereotype.Component;
import torres.kathya.Jump2Digital.models.SkinModel;

@Component
public class SkinConverter {
    // Convertir SkinModel a SkinDTO
    public SkinDTO modelToDto(SkinModel skinModel) {
        SkinDTO skinDTO = new SkinDTO();
        skinDTO.setId(skinModel.getId());
        skinDTO.setNombre(skinModel.getNombre());
        skinDTO.setTipo(skinModel.getTipo());
        skinDTO.setPrecio(skinModel.getPrecio());
        skinDTO.setColor(skinModel.getColor());
        return skinDTO;
    }

    // Convertir SkinDTO a SkinModel
    public SkinModel dtoToModel(SkinDTO skinDTO) {
        SkinModel skinModel = new SkinModel();
        // No establecemos el ID aquí si es generado automáticamente
        skinModel.setNombre(skinDTO.getNombre());
        skinModel.setTipo(skinDTO.getTipo());
        skinModel.setPrecio(skinDTO.getPrecio());
        skinModel.setColor(skinDTO.getColor());
        return skinModel;
    }
}

