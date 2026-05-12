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

    public void testDelete(int num)
    {
        service.deleteViking(num);
        gui.testDelete(num);
    }

    public void addRandomViking() {
        gui.addNewViking(service.createRandomViking());
    }


    public void addCrowd(int amount)
    {
        gui.addCrowd(service.createCrowd(amount));
    }

    public void addConcreteViking(
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

    public void editConcreteViking(
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
