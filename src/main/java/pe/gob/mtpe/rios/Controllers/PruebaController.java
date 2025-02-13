package pe.gob.mtpe.rios.Controllers;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/test")
public class PruebaController {


    @GetMapping
    public String getAll() {
        return "all";
    }

    @PostMapping
    public String create(@RequestBody String test) {
        return test;
    }

}