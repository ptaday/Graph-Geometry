package geometry;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Square extends Shape {

    public Point side1;
    public Point side2;
    public Point side3;
    public Point side4;


    public Square(Point a, Point b, Point c, Point d) throws IllegalArgumentException {

        try {

            if (checkValidity(a, b, c, d)) {
                this.side1 = a;
                this.side2 = b;
                this.side3 = c;
                this.side4 = d;
            } else
                throw new IllegalArgumentException("Invalid Square. Square with such coordinates cannot be formed. Make sure to input the points in the right order.");
        }
        catch (IllegalArgumentException e)
        {
            System.out.println(e.getMessage());
        }
    }

    private boolean checkValidity (Point first, Point second, Point third, Point fourth) {
        double distanceToBeChecked = 0;

        Point[] sides = {first, second, third, fourth,first};
        double distance=0;

        for (int i = 0; i < sides.length - 1; i++) {

            if (i == 0)
                distanceToBeChecked = Math.sqrt((Math.pow(sides[i].x - sides[i + 1].x, 2)) + (Math.pow(sides[i].y - sides[i + 1].y, 2)));

            else {
                distance = Math.sqrt((Math.pow(sides[i].x - sides[i + 1].x, 2)) + (Math.pow(sides[i].y - sides[i + 1].y, 2)));


                if (distance != distanceToBeChecked) {
                    return false;
                }
            }
        }

        double diagonal1=Math.sqrt((Math.pow(sides[0].x - sides[2].x, 2)) + (Math.pow(sides[0].y - sides[2].y, 2)));
        double diagonal2=Math.sqrt((Math.pow(sides[1].x - sides[3].x, 2)) + (Math.pow(sides[1].y - sides[3].y, 2)));

        if(diagonal1==diagonal2)
            return true;
        else
            return false;
    }


    public Point center() {

       double xCoordinateOfCenter= (this.side1.x+ this.side3.x)/2;
       double yCoordinateOfCenter= (this.side1.y+ this.side3.y)/2;

       Point centerPoint=new Point("Center of the square",xCoordinateOfCenter,yCoordinateOfCenter);

       return centerPoint;
    }


    public Square rotateBy(int degrees) {

      Square newSquare=this.clone();



        boolean checkDegree=false;

        if(degrees%90==0)
            checkDegree=true;


        double rad=Math.toRadians(degrees);

        Point center=newSquare.center();

        double translationRequiredForXAxis=0.0;
        double translationRequiredForYAxis=0.0;

        translationRequiredForXAxis=0.0- center.x;
        translationRequiredForYAxis=0.0- center.y;


        Square currentSquare=newSquare;

        boolean isTranslateRequired=true;

        if(translationRequiredForXAxis==0&&translationRequiredForYAxis==0)
            isTranslateRequired=false;

        if(isTranslateRequired)
        {
            currentSquare=(Square) (currentSquare.translateBy(translationRequiredForXAxis,translationRequiredForYAxis));
        }

        Point[] sides = {currentSquare.side1,currentSquare.side2,currentSquare.side3,currentSquare.side4};
        double newXPoint=0.0;
        double newYPoint=0.0;

        int c=0;


        for (int i = 0; i < sides.length; i++) {

            if(checkDegree){

                double sine= Math.sin(rad);
                double cosine=Math.cos(rad);

                if((sine>-0.1&&sine<0.01) || (sine>0.0&&sine<0.01))
                    sine=0;

                if((cosine>-0.1&&cosine<0.01) || (cosine>0.0&&cosine<0.01))
                    cosine=0;

                newXPoint = ((sides[i].x * cosine)-(sides[i].y * sine));
                newYPoint = ((sides[i].x * sine) + (sides[i].y * cosine));


            }
            else
            {
                newXPoint = ((sides[i].x * Math.cos(rad)) - (sides[i].y * Math.sin(rad)));
                if(Math.ceil(newXPoint)-newXPoint<0.001||newXPoint-Math.floor(newXPoint)<0.001)
                    newXPoint=Math.round(newXPoint);
                newYPoint = ((sides[i].x * Math.sin(rad)) + (sides[i].y * Math.cos(rad)));
                if(Math.ceil(newYPoint)-newYPoint<0.001||newYPoint-Math.floor(newYPoint)<0.001)
                    newYPoint=Math.round(newYPoint);
            }
            Point newPoint = new Point(sides[i].name, newXPoint, newYPoint);

            if(i==0)
                currentSquare.side1=newPoint;
            else if(i==1)
                currentSquare.side2=newPoint;
            else if(i==2)
                currentSquare.side3=newPoint;
            else
                currentSquare.side4=newPoint;

        }


        if(isTranslateRequired)
            currentSquare=(Square)((currentSquare.translateBy(0-translationRequiredForXAxis,0-translationRequiredForYAxis)));

       currentSquare.toString();

        return currentSquare;
    }

    public Shape translateBy(double x, double y) {

     //   Square newSquare=this.clone();

        Point[] sides = {this.side1,this.side2,this.side3,this.side4};

            for(int i=0;i< sides.length;i++)
            {
                Point newPoint= new Point (sides[i].name,sides[i].x+x,sides[i].y+y);
                if(i==0)
                    this.side1=newPoint;
                else if(i==1)
                    this.side2=newPoint;
                else if(i==2)
                   this.side3=newPoint;
                else
                    this.side4=newPoint;
            }


            return this;

    }

    @Override
    public String toString() {

        Point center=this.center();

        double translationRequiredForXAxis=0.0;
        double translationRequiredForYAxis=0.0;

        translationRequiredForXAxis=0.0- center.x;
        translationRequiredForYAxis=0.0- center.y;

        boolean isTranslateRequired=true;

        Square currentSquare=this;

        if(translationRequiredForXAxis==0&&translationRequiredForYAxis==0)
            isTranslateRequired=false;

        if(isTranslateRequired)
        {
            currentSquare=(Square)(currentSquare.translateBy(translationRequiredForXAxis,translationRequiredForYAxis));
        }
        Point[] sides = {currentSquare.side1,currentSquare.side2,currentSquare.side3,currentSquare.side4};

        Double[] degreesList=new Double[4];
        Point[]  pointList=sides;

        for(int i=0; i<sides.length;i++)
        {
            double degree=1; double angle=0; boolean innerFlag=true;

            if(sides[i].y==0 && sides[i].x<0) {
                degree = 180;
                innerFlag=false;
            }

            else
                angle= Math.atan2((sides[i].y),(sides[i].x));

            if(innerFlag) {
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
            degreesList[i]=degree;
        }

         int c=0;
        for(int i=0;i<degreesList.length;i++)
        {c=0;
            double min=degreesList[i]; Point minPoint=pointList[i]; int pos=i;
            for(int j=i+1;j<degreesList.length;j++)
            {
                if(min>degreesList[j]) {
                    min = degreesList[j];
                    minPoint=pointList[j];
                    pos=j; c++;
                }
            }
            if(c>0) {
                degreesList[pos] = degreesList[i];
                pointList[pos] = pointList[i];
                degreesList[i] = min;
                pointList[i] = minPoint;
            }
        }

        currentSquare.side1=pointList[0];
        currentSquare.side2=pointList[1];
        currentSquare.side3=pointList[2];
        currentSquare.side4=pointList[3];



        if(isTranslateRequired)
        {
            currentSquare=(Square)(currentSquare.translateBy(0-translationRequiredForXAxis,0-translationRequiredForYAxis));
        }


        return "["+currentSquare.side1.toString()+";"+currentSquare.side2.toString()+";"+currentSquare.side3.toString()+";"+currentSquare.side4.toString()+"]";
        //return "["+side1.toString()+";"+side2.toString()+";"+side3.toString()+";"+side4.toString()+"]";
    }

    protected Square clone()
    {


       Point[] ogSides= {this.side1, this.side2, this.side3, this.side4};
       Point[] newSides=new Point[4];


            for (int i = 0; i < ogSides.length; i++) {
                Point newPoint = new Point(ogSides[i].name, ogSides[i].x, ogSides[i].y);
                newSides[i]=newPoint;
            }

            return new Square(newSides[0],newSides[1],newSides[2],newSides[3]);
    }

    protected boolean equals(Square otherSquare)
    {
        Point[] ogSides= {this.side1, this.side2, this.side3, this.side4};
        Point[] otherSides= {otherSquare.side1, otherSquare.side2, otherSquare.side3, otherSquare.side4};

        for(int i=0;i<4;i++)
        {
            if(!ogSides[i].equals(otherSides[i]))
                return false;
        }
        return true;
    }

   public static void main(String[] args) {



            Point upright   = new Point("upright", 1, 1);
            Point upleft    = new Point("upleft", -1, 1);
            Point downleft  = new Point("downleft", -1, -1);
            Point downright = new Point("downright", 1, -1);
            Point east      = new Point("east", 1, 0);
            Point north     = new Point("north", 0, 1);
            Point west      = new Point("west", -1, 0);
            Point south     = new Point("south", 0, -1);

            Shape newSquare = new Square(north, south, east, west);

            Shape newSquare1=new Square(north, upleft, south, downright);

           Shape newSquare2= new Square(upright, upleft, downleft, downright);

           Shape newSquare3=new Square(east, north, west, south);

            System.out.println(newSquare2.center());
            System.out.println(newSquare3.center());





    }



}



