package geometry;

import java.util.*;

public class RadialGraph extends Shape {

    public Point centerOfRadialGraph;
    boolean isCenter;
    public List<Point> neighborsOfCenterPoint;

    public List<Double> sortedListOfDegrees;


    public RadialGraph(Point center, List<Point> neighbors) throws IllegalArgumentException {


        if (verifyDistances(center, neighbors)) {
            centerOfRadialGraph = center;
            neighborsOfCenterPoint = neighbors;
        } else
            throw new IllegalArgumentException("Edges are not of the same length.");

    }

    private boolean verifyDistances(Point currentCenter, List<Point> neighbors) {
        double distanceToBeChecked = 0.0;
        double currentDistance = 0.0;

        for (int i = 0; i < neighbors.size(); i++) {
            if (i == 0)
                distanceToBeChecked = Math.sqrt((Math.pow(currentCenter.x - neighbors.get(i).x, 2)) + (Math.pow(currentCenter.y - neighbors.get(i).y, 2)));

            else {
                currentDistance = Math.sqrt((Math.pow(currentCenter.x - neighbors.get(i).x, 2)) + (Math.pow(currentCenter.y - neighbors.get(i).y, 2)));

                if (currentDistance == distanceToBeChecked)
                    continue;

                else
                    return false;
            }
        }
        return true;
    }

    public RadialGraph(Point center) {
        centerOfRadialGraph = center;

        if (center.x == 0 && center.y == 0) {
            isCenter = true;
        } else {
            isCenter = false;
        }
    }

    public RadialGraph rotateBy(int degrees) {


        RadialGraph newRadialGraph = this.clone();

        boolean checkDegree = false;
        boolean istranslate = false;

        if (degrees % 90 == 0)
            checkDegree = true;

        double degree = Math.toRadians(degrees);

        double translationRequiredForXAxis = 0.0;
        double translationRequiredForYAxis = 0.0;

        translationRequiredForXAxis = 0.0 - newRadialGraph.centerOfRadialGraph.x;
        translationRequiredForYAxis = 0.0 - newRadialGraph.centerOfRadialGraph.y;

        RadialGraph current = null;

        if (translationRequiredForXAxis == 0.0 && translationRequiredForYAxis == 0) {
            current = newRadialGraph;
        } else {
            RadialGraph newGraph = this.translateBy(translationRequiredForXAxis, translationRequiredForYAxis);
            current = newGraph;
            istranslate = true;
        }


        if (current.neighborsOfCenterPoint != null) {
            List<Point> newListOfNeighborPoints = new ArrayList<>();
            double newXPoint = 0.0;
            double newYPoint = 0.0;

            int c = 0;


            for (int i = 0; i < current.neighborsOfCenterPoint.size(); i++) {

                if (checkDegree) {

                    double sine = Math.sin(degree);
                    double cosine = Math.cos(degree);

                    if ((sine > -0.1 && sine < 0.01) || (sine > 0.0 && sine < 0.01))
                        sine = 0;

                    if ((cosine > -0.1 && cosine < 0.01) || (cosine > 0.0 && cosine < 0.01))
                        cosine = 0;

                    newXPoint = ((current.neighborsOfCenterPoint.get(i).x * cosine) - (current.neighborsOfCenterPoint.get(i).y * sine));
                    newYPoint = ((current.neighborsOfCenterPoint.get(i).x * sine) + (current.neighborsOfCenterPoint.get(i).y * cosine));


                } else {
                    newXPoint = ((current.neighborsOfCenterPoint.get(i).x * Math.cos(degree)) - (current.neighborsOfCenterPoint.get(i).y * Math.sin(degree)));
                    if (Math.ceil(newXPoint) - newXPoint < 0.001 || newXPoint - Math.floor(newXPoint) < 0.001)
                        newXPoint = Math.round(newXPoint);
                    newYPoint = ((current.neighborsOfCenterPoint.get(i).x * Math.sin(degree)) + (current.neighborsOfCenterPoint.get(i).y * Math.cos(degree)));
                    if (Math.ceil(newYPoint) - newYPoint < 0.001 || newXPoint - Math.floor(newXPoint) < 0.001)
                        newYPoint = Math.round(newYPoint);
                }
                Point newPoint = new Point(neighborsOfCenterPoint.get(i).name, newXPoint, newYPoint);
                newListOfNeighborPoints.add(newPoint);
            }

            current.neighborsOfCenterPoint = newListOfNeighborPoints;


            if (istranslate)
                current = (current.translateBy(0 - translationRequiredForXAxis, 0 - translationRequiredForYAxis));

            return current;
        }

        if (istranslate)
            current = (current.translateBy(0 - translationRequiredForXAxis, 0 - translationRequiredForYAxis));


        return current;
    }


    protected RadialGraph rotateBy(double degrees) {


        RadialGraph newRadialGraph = this.clone();

        boolean checkDegree = false;
        boolean istranslate = false;

        if (degrees % 90.0 == 0)
            checkDegree = true;

        double degree = Math.toRadians(degrees);

        double translationRequiredForXAxis = 0.0;
        double translationRequiredForYAxis = 0.0;

        translationRequiredForXAxis = 0.0 - newRadialGraph.centerOfRadialGraph.x;
        translationRequiredForYAxis = 0.0 - newRadialGraph.centerOfRadialGraph.y;

        RadialGraph current = null;

        if (translationRequiredForXAxis == 0.0 && translationRequiredForYAxis == 0) {
            current = newRadialGraph;
        } else {
            RadialGraph newGraph = this.translateBy(translationRequiredForXAxis, translationRequiredForYAxis);
            current = newGraph;
            istranslate = true;
        }


        if (current.neighborsOfCenterPoint != null) {
            List<Point> newListOfNeighborPoints = new ArrayList<>();
            double newXPoint = 0.0;
            double newYPoint = 0.0;

            int c = 0;


            for (int i = 0; i < current.neighborsOfCenterPoint.size(); i++) {

                if (checkDegree) {

                    double sine = Math.sin(degree);
                    double cosine = Math.cos(degree);

                    if ((sine > -0.1 && sine < 0.01) || (sine > 0.0 && sine < 0.01))
                        sine = 0;

                    if ((cosine > -0.1 && cosine < 0.01) || (cosine > 0.0 && cosine < 0.01))
                        cosine = 0;

                    newXPoint = ((current.neighborsOfCenterPoint.get(i).x * cosine) - (current.neighborsOfCenterPoint.get(i).y * sine));
                    newYPoint = ((current.neighborsOfCenterPoint.get(i).x * sine) + (current.neighborsOfCenterPoint.get(i).y * cosine));


                } else {
                    newXPoint = ((current.neighborsOfCenterPoint.get(i).x * Math.cos(degree)) - (current.neighborsOfCenterPoint.get(i).y * Math.sin(degree)));
                    if (Math.ceil(newXPoint) - newXPoint < 0.001 || newXPoint - Math.floor(newXPoint) < 0.001)
                        newXPoint = Math.round(newXPoint);
                    newYPoint = ((current.neighborsOfCenterPoint.get(i).x * Math.sin(degree)) + (current.neighborsOfCenterPoint.get(i).y * Math.cos(degree)));
                    if (Math.ceil(newYPoint) - newYPoint < 0.001 || newXPoint - Math.floor(newXPoint) < 0.001)
                        newYPoint = Math.round(newYPoint);
                }
                Point newPoint = new Point(neighborsOfCenterPoint.get(i).name, newXPoint, newYPoint);
                newListOfNeighborPoints.add(newPoint);
            }

            current.neighborsOfCenterPoint = newListOfNeighborPoints;


            if (istranslate)
                current = (current.translateBy(0 - translationRequiredForXAxis, 0 - translationRequiredForYAxis));

            return current;
        }

        if (istranslate)
            current = (current.translateBy(0 - translationRequiredForXAxis, 0 - translationRequiredForYAxis));


        return current;
    }


    @Override
    public RadialGraph translateBy(double x, double y) {

        RadialGraph newGraph = this.clone();

        Point newCenterPoint = new Point("center", newGraph.centerOfRadialGraph.x + x, newGraph.centerOfRadialGraph.y + y);
        newGraph.centerOfRadialGraph = newCenterPoint;

        if (this.neighborsOfCenterPoint != null) {
            List<Point> newListOfPoints = new ArrayList<>();

            for (int i = 0; i < this.neighborsOfCenterPoint.size(); i++) {
                Point newPoint = new Point(neighborsOfCenterPoint.get(i).name, neighborsOfCenterPoint.get(i).x + x, neighborsOfCenterPoint.get(i).y + y);
                newListOfPoints.add(newPoint);
            }


            newGraph.neighborsOfCenterPoint = newListOfPoints;

            return newGraph;
        } else
            return newGraph;
    }


    @Override
    public String toString() {

        String s = "";
        s = s + "[" + this.centerOfRadialGraph.toString();

        if (this.neighborsOfCenterPoint != null) {

            RadialGraph newGraph = this.angleCounter(this.neighborsOfCenterPoint);

            for (int i = 0; i < newGraph.neighborsOfCenterPoint.size(); i++) {
                s = s + "; " + newGraph.neighborsOfCenterPoint.get(i).toString();
            }
        }

        s = s + "]";

        return s;
    }


    protected RadialGraph angleCounter(List<Point> pointList) {

        ArrayList<Point> points = new ArrayList<Point>();
        RadialGraph current = this;

        double translationRequiredForXAxis = 0.0;
        double translationRequiredForYAxis = 0.0;

        boolean flag = false;


        if (this.centerOfRadialGraph.x != 0.0 || this.centerOfRadialGraph.y != 0.0) {

            translationRequiredForXAxis = 0.0 - this.centerOfRadialGraph.x;
            translationRequiredForYAxis = 0.0 - this.centerOfRadialGraph.y;

            current = translateBy(translationRequiredForXAxis, translationRequiredForYAxis);
            flag = true;
        }

        List<Double> newArr = new ArrayList<Double>();
        for (int i = 0; i < current.neighborsOfCenterPoint.size(); i++) {
            double degree = 1;
            double angle = 0;
            boolean innerFlag = true;

            if (current.neighborsOfCenterPoint.get(i).y == 0 && current.neighborsOfCenterPoint.get(i).x < 0) {
                degree = 180;
                innerFlag = false;
            } else
                angle = Math.atan2((current.neighborsOfCenterPoint.get(i).y), (current.neighborsOfCenterPoint.get(i).x));

            if (innerFlag) {
                if (Math.toDegrees(angle) == -90.0)
                    degree = 270.0;
                else {
                    degree = Math.toDegrees(angle);


                    if (degree >= 0 && degree <= 180)
                        degree = degree;

                    else
                        degree = 360 + degree;
                }
            }
            newArr.add(degree);
            points.add(current.neighborsOfCenterPoint.get(i));
        }

        Double[] newArrDegrees = new Double[newArr.size()];
        Point[] newPointArr = new Point[newArr.size()];
        for (int i = 0; i < newArr.size(); i++) {
            newArrDegrees[i] = newArr.get(i);
            newPointArr[i] = points.get(i);
        }

        for (int i = 0; i < newArrDegrees.length; i++) {
            double min = newArrDegrees[i];
            Point minPoint = newPointArr[i];
            int pos = i;
            for (int j = i + 1; j < newArrDegrees.length; j++) {
                if (min > newArr.get(j)) {
                    min = newArrDegrees[j];
                    minPoint = newPointArr[j];
                    pos = j;
                }
            }
            newArrDegrees[pos] = newArrDegrees[i];
            newPointArr[pos] = newPointArr[i];
            newArrDegrees[i] = min;
            newPointArr[i] = minPoint;
        }

        sortedListOfDegrees = new ArrayList<Double>(Arrays.asList(newArrDegrees));

        List<Point> pointList1 = new ArrayList<>(Arrays.asList(newPointArr));
        current.neighborsOfCenterPoint = pointList1;

        if (flag) {
            current = current.translateBy(0 - translationRequiredForXAxis, 0 - translationRequiredForYAxis);
        }

        return current;
    }


    @Override
    public Point center() {
        return this.centerOfRadialGraph;
    }

    protected boolean equals(RadialGraph graph) {
        if (this.neighborsOfCenterPoint.size() == graph.neighborsOfCenterPoint.size()) {
            if (this.centerOfRadialGraph.equals(graph.centerOfRadialGraph)) {

                for (int i = 0; i < this.neighborsOfCenterPoint.size(); i++) {
                    if (!this.neighborsOfCenterPoint.get(i).equals(graph.neighborsOfCenterPoint.get(i)))
                        return false;
                }
                return true;
            } else
                return false;
        } else
            return false;
    }

    protected RadialGraph clone() {
        Point newCenter = new Point(this.centerOfRadialGraph.name, this.centerOfRadialGraph.x, this.centerOfRadialGraph.y);

        List<Point> newNeighbors = new ArrayList<>();

        if (this.neighborsOfCenterPoint != null) {


            for (int i = 0; i < this.neighborsOfCenterPoint.size(); i++) {
                Point newPoint = new Point(this.neighborsOfCenterPoint.get(i).name, this.neighborsOfCenterPoint.get(i).x, this.neighborsOfCenterPoint.get(i).y);
                newNeighbors.add(newPoint);
            }

            return new RadialGraph(newCenter, newNeighbors);
        } else
            return new RadialGraph(newCenter);
    }

    /* Driver method given to you as an outline for testing your code. You can modify this as you want, but please keep
     * in mind that the lines already provided here as expected to work exactly as they are (some lines have additional
     * explanation of what is expected) */
    public static void main(String... args) {
        try {
            Point center = new Point("center", 0, 0);
            Point east = new Point("east", 1, 0);
            Point west = new Point("west", -1, 0);
            Point north = new Point("north", 0, 1);
            Point south = new Point("south", 0, -1);
            Point toofarsouth = new Point("south", 0, -8);

            // A single node is a valid radial graph.
            RadialGraph lonely = new RadialGraph(center);

            // Must print: [(center, 0.0, 0.0)]
            System.out.println(lonely);

            lonely = lonely.rotateBy(90);
            System.out.println(lonely);


            // This line must throw IllegalArgumentException, since the edges will not be of the same length
            // RadialGraph nope = new RadialGraph(center, Arrays.asList(north, toofarsouth, east, west));

            Shape g = new RadialGraph(center, Arrays.asList(north, south, east, west));

            // Must follow the documentation in the Shape abstract class, and print the following string:
            // [(center, 0.0, 0.0); (east, 1.0, 0.0); (north, 0.0, 1.0); (west, -1.0, 0.0); (south, 0.0, -1.0)]
            System.out.println(g);


            // After this counterclockwise rotation by 90 degrees, "north" must be at (-1, 0), and similarly for all the
            // other radial points. The center, however, must remain exactly where it was.
            RadialGraph g1 = (RadialGraph) g.rotateBy(45);

            System.out.println(g1);

            System.out.println(g.rotateBy(45));
            // you should similarly add tests for the translateBy(x, y) method

            //System.out.println(g.rotateBy(90));

            //System.out.println(g);

        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }
}