package fairyShop.repositories;

import fairyShop.models.Present;
import fairyShop.models.PresentImpl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class PresentRepository<T> implements Repository<Present> {

    private List<Present> presents;

    public PresentRepository() {
        this.presents = new ArrayList<>();
    }

    @Override
    public Collection<Present> getModels() {
        return Collections.unmodifiableList(this.presents);
    }

    @Override
    public void add(Present model) {
        presents.add(model);
    }

    @Override
    public boolean remove(Present model) {
        return presents.remove(model);
    }

    @Override
    public Present findByName(String name) {
        return presents.stream().filter(present -> present.getName().equals(name)).findFirst().orElse(null);
    }
}
