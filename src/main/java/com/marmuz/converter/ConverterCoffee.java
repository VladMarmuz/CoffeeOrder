package com.marmuz.converter;

import com.marmuz.ejb.CoffeeEJB;
import com.marmuz.models.Coffee;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

@FacesConverter("converter.CoffeeConverter")
public class ConverterCoffee implements Converter {

    CoffeeEJB  coffeeEJB = new CoffeeEJB();

    @Override
    public Object getAsObject(FacesContext facesContext, UIComponent uiComponent,String s) {
        Coffee coffee = coffeeEJB.getByType(Integer.parseInt(s));
        return (Object) coffee;
    }

    @Override
    public String getAsString(FacesContext facesContext, UIComponent uiComponent, Object o) {
        return o.toString();
    }
}
