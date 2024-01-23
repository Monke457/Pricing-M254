package com.bsfh.pricing.controller;

import com.bsfh.pricing.model.PriceHistory;
import com.bsfh.pricing.model.Product;
import com.bsfh.pricing.service.DBService;
import com.bsfh.pricing.service.HistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Controller
public class MainController {

    @Autowired
    private DBService<Product> productService;
    @Autowired
    private HistoryService historyService;

    @GetMapping("/")
    public String getIndex(Model model) {
        model.addAttribute("entries", productService.getAll(Product.class));
        return "index";
    }

    @GetMapping("/create")
    public String create(Model model) {
        model.addAttribute("entry", new Product());
        model.addAttribute("history", new HashSet<>());
        return "create";
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public String create(@ModelAttribute Product item, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("entry", item);
            model.addAttribute("history", new HashSet<>());
            return "create";
        }
        productService.create(item);
        historyService.create(new PriceHistory(item, LocalDateTime.now(), item.getPrice()));
        return "redirect:/";
    }

    @GetMapping("/edit")
    public String edit(@RequestParam UUID id,Model model) {
        Product item = productService.find(Product.class, id);

        if (item == null) return "redirect:/";
        model.addAttribute("history", historyService.findByProduct(id).collect(Collectors.toSet()));
        model.addAttribute("entry", item);
        return "edit";
    }

    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    public String edit(@RequestParam UUID id, @ModelAttribute Product item,BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("entry", item);
            model.addAttribute("history", historyService.findByProduct(id).collect(Collectors.toSet()));
            return "edit";
        }
        productService.update(item);
        historyService.create(new PriceHistory(item, LocalDateTime.now(), item.getPrice()));
        return "redirect:/";
    }

    @GetMapping("/delete")
    public String delete(@RequestParam UUID id, Model model) {
        Product item = productService.find(Product.class, id);

        if (item == null) return "redirect:/";

        model.addAttribute("entry", item);
        return "delete";
    }

    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public String delete(@RequestParam UUID id, @ModelAttribute Product item, BindingResult bindingResult) {
        if (!bindingResult.hasErrors()) {
            productService.delete(Product.class, item);
        }
        return "redirect:/";
    }

    @GetMapping("/history")
    public String history(@RequestParam UUID id, Model model) {
        model.addAttribute("product", productService.find(Product.class, id).getTitle());
        model.addAttribute("entries", historyService.findByProduct(id));
        return "/history";
    }
}