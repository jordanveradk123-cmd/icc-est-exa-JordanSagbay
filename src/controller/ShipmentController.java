package controller;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

import models.Shipment;

public class ShipmentController {
  public Set<Shipment> filterAndOrderShipments(List<Shipment> shipments, double minimunVolumen) {
    Set<Shipment> filter = new TreeSet<>(
        (p1, p2) -> {
          int compV = Double.compare(p2.getVolumen(), p1.getVolumen());
          if (compV != 0) {
            return compV;
          }
          int comT = p1.getTrackingCode().compareToIgnoreCase(p2.getTrackingCode());
          if (comT != 0) {
            return comT;
          }
          return 0;
        });
    for (Shipment shipment : shipments) {
      if (shipment.getVolumen() >= minimunVolumen) {
        filter.add(shipment);

      }

    }
    return filter;

  }

  public List<Shipment>classifyAndExtractShipments(List<Shipment>shimpments , String requestedCategory){
    Map<String,Set<String>>filtrado = new ArrayList<>();
    filtrado.put("BULKY", new LinkedHashSet<>());
    filtrado.put("REGULAR", new LinkedHashSet<>());
    filtrado.put("LIGHT", new LinkedHashSet<>());
    for (Shipment shipment : shimpments) {
        String com =Double.toString(shipment.getWeight());
      if(shipment.getVolumen()>3000){
        filtrado.get("BULKY").add(com);
      }else  if(shipment.getVolumen()>=120000 or shipment.getWeight()>25){
        filtrado.get("REGULAR").add(com);
      } else{
        filtrado.get("LIGHT").add(com);
      }
      
    }
    return filtrado;

    


  }

}
