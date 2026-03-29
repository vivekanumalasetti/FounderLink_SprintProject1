package com.founderlink.startupservice.controller;

import com.founderlink.startupservice.entity.Startup;
import com.founderlink.startupservice.service.StartupService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/startups")
@RequiredArgsConstructor
public class StartupController {

    private final StartupService startupService;

    @PostMapping
    public ResponseEntity<Startup> create(
            @RequestBody Startup startup,
            @RequestHeader("X-User-Id") Long founderId) {

        startup.setFounderId(founderId);
        return ResponseEntity.ok(startupService.create(startup));
    }

    @GetMapping
    public ResponseEntity<List<Startup>> search(
            @RequestParam(required = false) String industry,
            @RequestParam(required = false) String stage) {

        return ResponseEntity.ok(startupService.search(industry, stage));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Startup> getById(@PathVariable Long id) {
        return ResponseEntity.ok(startupService.getById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Startup> update(
            @PathVariable Long id,
            @RequestBody Startup startup) {

        return ResponseEntity.ok(startupService.update(id, startup));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        startupService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}/approve")
    public ResponseEntity<Startup> approve(@PathVariable Long id) {
        return ResponseEntity.ok(startupService.approve(id));
    }
}