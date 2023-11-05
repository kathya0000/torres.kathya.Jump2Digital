package torres.kathya.Jump2Digital.controllers;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import torres.kathya.Jump2Digital.dto.SkinDTO;
import torres.kathya.Jump2Digital.security.repository.UserRepository;
import torres.kathya.Jump2Digital.security.user.User;
import torres.kathya.Jump2Digital.services.SkinService;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;


@RestController
@AllArgsConstructor
@RequestMapping("/api/skins")
public class SkinController {

        @Autowired
        private final SkinService skinService;
        private final UserRepository userRepository;


    @PostMapping("/add")
    public ResponseEntity<SkinDTO> addSkin(@Valid @RequestBody SkinDTO skinDTO) {
        SkinDTO newSkin = skinService.addNewSkin(skinDTO);
        return new ResponseEntity<>(newSkin, HttpStatus.CREATED);
    }

    @GetMapping("/available")
    public ResponseEntity<List<SkinDTO>> getAvailableSkins() {
        List<SkinDTO> skins = skinService.getSkins();
        return ResponseEntity.ok(skins);
    }

    @GetMapping("/myskins")
    public ResponseEntity<List<SkinDTO>> getMySkins() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userEmail = authentication.getName();
        User user = userRepository.findByEmail(userEmail).orElse(null);
        if (user == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        Long userId = user.getId();
        List<SkinDTO> skins = skinService.getSkinsByUserId(userId);
        return ResponseEntity.ok(skins);
    }


   @PostMapping("/buy")
   public ResponseEntity<SkinDTO> buySkin(@RequestBody Map<String, Long> payload) {
       Long skinId = payload.get("skinId");
       SkinDTO purchasedSkin = skinService.purchaseSkin(skinId);
       return new ResponseEntity<>(purchasedSkin, HttpStatus.CREATED);
   }


    @GetMapping("/{id}")
        public ResponseEntity<SkinDTO> getSkinById(@PathVariable Long id) {
         SkinDTO skinDTO = skinService.getById(id);
         return ResponseEntity.ok(skinDTO);
    }


       @PutMapping("/{id}/color")
       public ResponseEntity<SkinDTO> updateSkinColor(@PathVariable Long id, @RequestParam String color) {
        SkinDTO updatedSkin = skinService.updateSkinColor(id, color);
        return ResponseEntity.ok(updatedSkin);
    }

      @DeleteMapping("/{id}")
        public ResponseEntity<Void> deleteSkin(@PathVariable Long id) {
         boolean wasDeleted = skinService.deleteSkin(id);
           if (wasDeleted) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
}


