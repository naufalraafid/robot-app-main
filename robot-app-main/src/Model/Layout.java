package Model;

public class Layout {
    private char[][] area;
    private char icon;
    private int maxX = 0;
    public int getMaxX() {
        return maxX;
    }

    private int maxY = 0;

    public int getMaxY() {
        return maxY;
    }

    public char[][] getArea() {
        return area;
    }

    public void updateRobotPosition(int x, int y, char robotIcon) {
        area[y][x] = robotIcon;
    }

    public void draw() {
        for (int i = 0; i < maxY; i++) {
            for (int j = 0; j < maxX; j++) {
                System.out.print(area[i][j]);
            }
            System.out.println();
        }
    }
    public Layout(int maxX, int maxY, char icon) {
        this.maxX = maxX;
        this.maxY = maxY;
        this.icon = icon;
        area = new char[maxX][maxY];
        for(int i = 0; i < area.length; i++) {
            for(int j = 0; j < area[i].length; j++) {
                area[i][j] = this.icon;
            }
        }
    }
}
