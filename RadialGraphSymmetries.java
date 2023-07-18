package geometry;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;


public class RadialGraphSymmetries implements Symmetries<RadialGraph> {

    double constantAngle;
    @Override
    public boolean areSymmetric(RadialGraph s1, RadialGraph s2) {

           List<RadialGraph> list= (ArrayList<RadialGraph>) symmetriesOf(s1);
           RadialGraph otherRadialGraph= (RadialGraph) s2;

           otherRadialGraph.angleCounter(otherRadialGraph.neighborsOfCenterPoint);

           for(int i=0;i<list.size();i++)
           {
               if(list.get(i).equals(otherRadialGraph))
               {
                   return true;
               }
           }
           return false;



    }

    @Override
    public List<RadialGraph> symmetriesOf(RadialGraph shape) {

        List<RadialGraph> listOfSymmetries=new ArrayList<>();

           RadialGraph radialGraph=(RadialGraph) shape ;
           listOfSymmetries.add(radialGraph);
            radialGraph.angleCounter(radialGraph.neighborsOfCenterPoint);
           List<Double> listOfAngles=radialGraph.sortedListOfDegrees;

           if(checkDegrees(listOfAngles))
           {
               int numberOfRotations=(int)(360.0/constantAngle);
               RadialGraph temp=null;   RadialGraph graph=null;
               for(int i=0; i<numberOfRotations-1;i++)
               {
                   if(i==0) {
                      graph = radialGraph.rotateBy(constantAngle);
                       temp=graph;
                   }
                   else {
                       temp = temp.rotateBy(constantAngle);
                   }
                   temp.angleCounter(temp.neighborsOfCenterPoint);
                   listOfSymmetries.add(temp);
               }

           }
           return listOfSymmetries;
    }

    private boolean checkDegrees(List<Double>listToBeChecked)
    {
        if(listToBeChecked.size()>1) {
            double prev = 0;
            double checkingValue = 0;
            int c = 0;
            for (int i = 0; i < listToBeChecked.size(); i++) {
                if (i == 0)
                    prev = listToBeChecked.get(i);

                else {
                    if (c == 0) {
                        checkingValue = listToBeChecked.get(i) - prev;
                        c++;
                        prev = listToBeChecked.get(i);
                        continue;
                    }
                    double value = 0;

                    if (i == listToBeChecked.size() - 1)
                        value = 360-listToBeChecked.get(i)+listToBeChecked.get(0) ;

                    else
                        value = listToBeChecked.get(i) - prev;

                    prev = listToBeChecked.get(i);

                    if (value == checkingValue)
                        continue;

                    else
                        return false;

                }
            }
            constantAngle = checkingValue;
            return true;
        }
        else
        {
            if(listToBeChecked==null)
                return false;

            else
            {
                if(listToBeChecked.get(0)==180) {
                    constantAngle=180;
                    return true;
                }

                else
                    return false;
            }
        }
    }

    public static void main(String[] args) {
        Point center    = new Point("center", 1, 1);
        Point north     = new Point("north", 1, 3);
        Point southwest = new Point("southwest", 1 - Math.sqrt(3), 0);
        Point southeast = new Point("southeast", 1 + Math.sqrt(3), 0);

        RadialGraph radialGraphSymmetries= new RadialGraph(center, Arrays.asList(north, southeast, southwest));
    }
}
