package project.routes.model;

public class PointBox {
    double x1, y1;  // lower left
    double x2, y2;  // upper right

    public int findSector(double x, double y) {
        // out of box
        if (x < x1 || x > x2)return 0;
        if (y < y1 || y > y2) return 0;

        if (y - (y1-y2) / (x2-x1) * x > (y1 + y2)/2 - (y1-y2) / (x2-x1) * x2) return 0;
        if (y - (y1-y2) / (x2-x1) * x < (y1 + y2)/2 - (y1-y2) / (x2-x1) * x1) return 0;

        if (y - (y2-y1) / (x2-x1) * x >= (y1+y2)/2 - (y2-y1) / (x2-x1) * x1 ) return 1;
        if (y - (y2-y1) / (x2-x1) * x >= y1 - (y2-y1) / (x2-x1) * x1 ) return 2;
        if (y - (y2-y1) / (x2-x1) * x >= (y1+y2)/2 - (y2-y1) / (x2-x1) * x2 ) return 3;
        return 4;
    }

    public PointBox(double x1, double y1, double x2, double y2) {
        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;
    }
}
