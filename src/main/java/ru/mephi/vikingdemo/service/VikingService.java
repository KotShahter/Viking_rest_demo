package ru.mephi.vikingdemo.service;

import org.springframework.stereotype.Service;
import ru.mephi.vikingdemo.model.BeardStyle;
import ru.mephi.vikingdemo.model.HairColor;
import ru.mephi.vikingdemo.model.Viking;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.stream.IntStream;

import org.springframework.beans.factory.annotation.Autowired;

@Service
public class VikingService {
    // каждый раз при изменении создаётся новая копия списка 
    private final CopyOnWriteArrayList<Viking> vikings = new CopyOnWriteArrayList<>();
    private final VikingFactory vikingFactory;

    @Autowired
    public VikingService(VikingFactory vikingFactory) {
        this.vikingFactory = vikingFactory;
    }
    
    public List<Viking> findAll() {
        return List.copyOf(vikings);
    }

    public void deleteViking(int id) {
        vikings.remove(id);
    }

    public List<Viking> createCrowd(int amount)
    {
        List<Viking> viks = new ArrayList<>();
        IntStream.range(0, amount).forEach(counter -> viks.add(vikingFactory.createRandomViking()));
        vikings.addAll(viks);
        return viks;
    }

    public Viking createRandomViking() {

        Viking viking = vikingFactory.createRandomViking();

        vikings.add(viking);
        return viking;
    }

    public Viking createConcreteViking(
            String name,
            Integer age,
            int heightCm,
            HairColor hairColor,
            BeardStyle beardStyle)
    {
        Viking viking = vikingFactory.createConcreteViking(
            name,
            age,
            heightCm,
            hairColor,
            beardStyle);

        vikings.add(viking);
        return viking;
    }

    public Viking editViking(
            int id,
            String name,
            Integer age,
            Integer heightCm,
            HairColor hairColor,
            BeardStyle beardStyle
    )
    {
        Viking existing = vikings.get(id);

        name = (name == null ? existing.name() : name);
        age = (age == null ? existing.age() : age);
        heightCm = (heightCm == null ? existing.heightCm() : heightCm);
        hairColor = (hairColor == null ? existing.hairColor() : hairColor);
        beardStyle = (beardStyle == null ? existing.beardStyle() : beardStyle);

        Viking vik = vikingFactory.createConcreteViking (
                name, age, heightCm, hairColor, beardStyle, existing.equipment());
        vikings.set(id, vik);
        return vik;
    }
}
