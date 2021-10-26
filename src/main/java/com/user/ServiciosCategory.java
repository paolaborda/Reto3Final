/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.user;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author USUARIO
 */
@Service
public class ServiciosCategory {
     @Autowired
    private RepositorioCategory metodosCrud;

    public List<Category> getAll() {
        return metodosCrud.getAll();
    }

    public Optional<Category> getCategory(int id) {
        return metodosCrud.getCategory(id);
    }

    public Category save(Category category) {
        if (category.getId()== null) {
            return metodosCrud.save(category);
        } else {
            Optional<Category> categoria1 = metodosCrud.getCategory(category.getId());
            if (categoria1.isEmpty()) {
                return metodosCrud.save(category);
            } else {
                return category;
            }
        }
    }
    public Category update(Category category){
        if (category.getId()!=null){
            Optional<Category> evt = metodosCrud.getCategory(category.getId());
            if (!evt.isEmpty()){
                if (category.getDescription()!=null){
                    evt.get().setDescription(category.getDescription());
                }
                if (category.getName()!=null){
                    evt.get().setName(category.getName());
                }
                return metodosCrud.save(evt.get());
            }
        }
        return category;
    }
    
    public boolean deleteCategory(int id){
        Boolean del = getCategory(id).map(category -> {
            metodosCrud.delete(category);
            return true;
        }).orElse(false);
        return del;
    }
}

