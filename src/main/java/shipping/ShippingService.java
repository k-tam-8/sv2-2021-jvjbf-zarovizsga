package shipping;

import java.util.*;

public class ShippingService {

    private List<Transportable> packages = new ArrayList<>();

    public void addPackage(Transportable newPackage){
        packages.add(newPackage);
    }

    public List<Transportable> getPackages() {
        return new ArrayList<>(packages);
    }

    public  List<Transportable> collectItemsByBreakableAndWeight(boolean breakable, int weight){
        return packages.stream().filter(t->t.isBreakable()==breakable).filter(t->t.getWeight()>=weight).toList();
    }

    public Map<String, Integer> collectTransportableByCountry(){
        Map<String, Integer> result = new HashMap<>();
        for (Transportable tr : packages){
            if (!result.containsKey(tr.getDestinationCountry())){
                result.put(tr.getDestinationCountry(),1);
            } else result.put(tr.getDestinationCountry(),result.get(tr.getDestinationCountry())+1);
        }
        return result;
    }

    public List<Transportable> sortInternationalPackagesByDistance(){
        return packages.stream().filter(t->t instanceof InternationalPackage)
                .sorted(Comparator.comparing(t->((InternationalPackage) t).getDistance())).toList();
    }
}
