package com.bosonit.block11uploaddownloadfiles.controllers;

import com.bosonit.block11uploaddownloadfiles.DTOs.ArchivoInput;
import com.bosonit.block11uploaddownloadfiles.DTOs.ArchivoOutput;
import com.bosonit.block11uploaddownloadfiles.application.services.ArchivoService;
import com.bosonit.block11uploaddownloadfiles.application.services.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Date;

@RestController
public class ArchivoUploadController {

    private final StorageService storageService;
    @Autowired
    ArchivoService archivoService;

    @Autowired
    public ArchivoUploadController(StorageService storageService) {
        this.storageService = storageService;
    }


    @PostMapping("upload")
    public ResponseEntity<ArchivoOutput> handleFileUpload(@RequestParam("file") MultipartFile file,
                                                                  RedirectAttributes redirectAttributes) {
        storageService.store(file);

        Date date = new Date();
        ArchivoInput datosFicheroInputDto = new ArchivoInput(file.getOriginalFilename(), date);

        redirectAttributes.addFlashAttribute("message", "You successfully uploaded " + file.getOriginalFilename() + "!");
        return ResponseEntity.ok().body(archivoService.addMetadatos(datosFicheroInputDto));
    }

    @GetMapping("filename/{filename:.+}")
    @ResponseBody
    public ResponseEntity<Resource> serveFile(@PathVariable String filename) {

        Resource file = storageService.loadAsResource(filename);
        return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION,
                "attachment; filename=\"" + file.getFilename() + "\"").body(file);
    }

    @GetMapping("fileid/{id}")
    @ResponseBody
    public ResponseEntity<Resource> serverFileById(@PathVariable int id) {
        Resource file = storageService.loadAsResourceById(id);
        return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION,
                "attachment; filename=\"" + file.getFilename() + "\"").body(file);
    }
}
