package ru.mephi.vikingdemo.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;
import ru.mephi.vikingdemo.model.BeardStyle;
import ru.mephi.vikingdemo.model.HairColor;
import ru.mephi.vikingdemo.model.Viking;
import ru.mephi.vikingdemo.service.VikingService;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

@RestController
@RequestMapping("/api/vikings")
@Tag(name = "Vikings", description = "Операции с викингами")
public class VikingController {

    private final VikingService vikingService;
    private final VikingListener vikingListener;

    public VikingController(VikingService vikingService, VikingListener vikingListener) {
        this.vikingService = vikingService;
        this.vikingListener = vikingListener;
    }
    
    @GetMapping
    @Operation(summary = "Получить список созданных викингов", 
            operationId = "getAllVikings")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Список успешно получен")
    })
    public List<Viking> getAllVikings() {
        System.out.println("GET /api/vikings called");
        return vikingService.findAll();
    }


    @DeleteMapping
    @Operation(summary = "Удалить викинга по номеру",
            operationId = "removeViking")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Викинг успешно удален")
    })
    public void removeViking(@RequestParam int id) {
        vikingListener.testDelete(id);
            System.out.println("DELETE /api/vikings");
    }


    @GetMapping("/test")
    @Operation(summary = "Получить список тестовых викингов", 
            operationId = "getTest")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Список успешно получен")
    })
    public List<String> test() {
        System.out.println("GET /api/vikings/test called");
        return List.of("Ragnar", "Bjorn");
    }

    @PostMapping("/random")
    public void addRandomViking(){
        vikingListener.addRandomViking();
    }

    @PostMapping("/concrete")
    @Operation(summary = "Создать конкретного викинга",
            operationId = "Create concrete viking")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Успешно добавлили")
    })
    public void addConcreteViking(
            @RequestParam String name,
            @RequestParam int age,
            @RequestParam int heightCm,
            @RequestParam HairColor hairColor,
            @RequestParam BeardStyle beardStyle) {
        System.out.println("Post /api/vikings/concrete");
        vikingListener.addConcreteViking(
                name,
                age,
                heightCm,
                hairColor,
                beardStyle);
    }

    @PatchMapping
    @Operation(summary = "Изменить викинга",
            operationId = "ChangeViking")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Успешно изменили викинга")
    })
    public void editConcreteViking(
            @RequestParam int id,
            @RequestParam (required = false) String name,
            @RequestParam (required = false) Integer age,
            @RequestParam (required = false) Integer heightCm,
            @RequestParam (required = false) HairColor hairColor,
            @RequestParam (required = false) BeardStyle beardStyle) {

        vikingListener.editConcreteViking(id, name, age, heightCm, hairColor, beardStyle);
    }

    @PostMapping("/crowd")
    public void addVikings(
            @RequestParam int amount
    )
    {
        vikingListener.addCrowd(amount);
    }
}
