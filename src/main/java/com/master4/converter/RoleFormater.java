package com.master4.converter;

import com.master4.entities.Role;
import lombok.SneakyThrows;
import org.springframework.beans.propertyeditors.CustomCollectionEditor;

import java.util.Collection;

public class RoleFormater extends CustomCollectionEditor {
    public RoleFormater(Class<? extends Collection> collectionType ) {
        super(collectionType);
    }

    @SneakyThrows
    @Override
    protected Object convertElement(Object element) {
        if(!element.equals(null)) {
            Role role =  new Role(Long.parseLong(String.valueOf(element)));
            return role;
        }else{
            return null;
        }
    }
}
