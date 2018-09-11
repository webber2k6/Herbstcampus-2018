package com.cc.spring.endpoints;

import com.cc.spring.domain.Produkt;
import com.cc.spring.domain.User;
import com.cc.spring.util.RestUtils;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class RestEndpoints
{
    @GetMapping("/hello")
    public String getHello()
    {
        String result = "Hallo, Welt!";
        System.out.println(result);
        return result;
    }

    @PostMapping(value = "/hello", params = {"name"})
    @ResponseStatus(HttpStatus.CREATED)
    public String postHello(@RequestParam(value = "name") String name)
    {
        String result = "Hello, " + name + "!";
        System.out.println(result);
        return result;
    }

    @PutMapping(value = "/hello/{name}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public String putHello(@PathVariable(value = "name") String name)
    {
        String result = "Hello, " + name + "!";
        System.out.println(result);
        return result;
    }

    @PostMapping(value = "/product.json", produces = "application/json")
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    public Produkt productJson(@RequestBody User user) {
        System.out.println(user.getName().getVorname() + " " + user.getName().getNachname());
        return RestUtils.generateSampleProdukt(user);
    }

    @PostMapping(value = "/product.xml", produces = "application/xml")
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    public Produkt productXml(@RequestBody User user) {
        System.out.println(user.getName().getVorname() + " " + user.getName().getNachname());
        return RestUtils.generateSampleProdukt(user);
    }
}
