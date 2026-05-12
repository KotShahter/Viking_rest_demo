/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ru.mephi.vikingdemo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.mephi.vikingdemo.gui.VikingDesktopFrame;
import ru.mephi.vikingdemo.model.BeardStyle;
import ru.mephi.vikingdemo.model.HairColor;
import ru.mephi.vikingdemo.service.VikingService;

import java.util.stream.IntStream;

/**
 *
 * @author test2023
 */
@Component
public class VikingListener {
    private VikingService service;
    private VikingDesktopFrame gui;

    @Autowired
    public VikingListener(VikingService service) {
        this.service = service;
    }
    
    public void setGui(VikingDesktopFrame gui){
        this.gui = gui;
    }

    void testDelete(int num)
    {
        service.deleteViking(num);
        gui.testDelete(num);
    }

    void addRandomViking() {
        gui.addNewViking(service.createRandomViking());
    }

    void addCrowd(int amount)
    {
        IntStream.range(0, amount).forEach(b -> gui.addNewViking(service.createRandomViking()));
    }

    void addConcreteViking(
            String name,
            int age,
            int heightCm,
            HairColor hairColor,
            BeardStyle beardStyle)
    {
        gui.addNewViking(service.createConcreteViking(
            name,
            age,
            heightCm,
            hairColor,
            beardStyle)
        );
    }

    void editConcreteViking(
        int id,
        String name,
        Integer age,
        Integer heightCm,
        HairColor hairColor,
        BeardStyle beardStyle)

    {
        gui.editViking(id, service.editViking(id, name, age, heightCm, hairColor, beardStyle));
    }

}
