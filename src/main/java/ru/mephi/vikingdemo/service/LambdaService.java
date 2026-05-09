package ru.mephi.vikingdemo.service;

import org.springframework.stereotype.Service;
import ru.mephi.vikingdemo.model.BeardStyle;
import ru.mephi.vikingdemo.model.HairColor;
import ru.mephi.vikingdemo.model.Viking;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@Service
public class LambdaService {
    private final VikingService vikingService;

    public LambdaService(VikingService vikingService) {
        this.vikingService = vikingService;
    }

    public long ageFilter() {
        return vikingService.findAll().stream().filter(vik -> vik.age() > 20).count();
    }

    public long hairFilter() {
        return vikingService.findAll().stream().filter
                (vik -> vik.hairColor() == HairColor.Blond && vik.beardStyle() == BeardStyle.BRAIDED)
                .count();
    }

    public long axeFilter() {
        return vikingService.findAll().stream().filter(viking ->

                viking.equipment().stream().filter(eq -> eq.name().equals("axe")).count() > 0

        ).count();
    }

    public List<Integer> evenID (List <Integer> ID)
    {
        return ID.stream().filter(id -> id % 2 == 0).toList();
    }

    public Optional<Integer> lastID (List <Integer> ID)
    {
        return ID.stream().max(Comparator.comparingInt(x -> x));
    }

    public List<Viking> heightFilter() {
        return vikingService.findAll().stream().filter(vik -> vik.heightCm() > 180)
                .toList();
    }

    public List<Viking> rareFilter() {
        return vikingService.findAll().stream().filter(viking ->

                viking.equipment().stream().anyMatch(eq -> eq.quality().equals("Legendary"))

        ).toList();
    }

    public List<Viking> gingerFilter() {
        return vikingService.findAll().stream().filter
                (vik -> vik.hairColor() == HairColor.Red)
                .sorted((x1, x2) -> Integer.compare(x1.age(), x2.age()))
                .toList();
    }
}