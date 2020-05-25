package by.yavid.Repository.Yavid;

import by.yavid.Entity.Yavid.Model;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ModelRepository extends CrudRepository<Model, Integer> {
    public List<Model> findByCodIsIn(List<String> cods);
    public Model findFirstByCod(String cod);
}
