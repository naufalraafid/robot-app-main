import java.util.Scanner;
import Model.Layout;
import Model.Position;
import Model.Robot;

public class RobotApp {
    public static void main(String[] args){
        new RobotApp();
    }

    /*
     * Perintah Soal:
     * Anggap 'o' adalah sebuah robot yang berada didalam area permainan.
     * Buat robot dapat bergerak ke kiri/kanan/atas/bawah sesuai jumlah langkah yang diinstruksikan.
     * Format instruksi: {d=kanan/a=kiri/w=atas/s=bawah}{jumlah langkah} dan 'x' untuk keluar aplikasi *case sensitive
     * Robot tidak boleh keluar dari area permainan dan buat pesan kesalahannya.
     * Buat pesan kesalahan jika pengguna tidak menulis instruksi dengan benar.
     * Lanjut pada perintah soal dibawah.
     */

    private Layout layout;
    private Robot robot;
    private Scanner scanner;
    public RobotApp() {
        // contoh konfigurasi (inisiasi object layout) area permainan: X = 10, Y = 10, icon area yang tidak ditempati robot adalah '*'
        this.layout = new Layout(10, 10, '*');
        this.scanner = new Scanner(System.in);
        this.robot = new Robot('o', new Position(0, 0));
        String instruction = "";
        System.out.println("-------- Permainan Dimulai --------");
        do{
            draw();
            instruction = waitInstruction();
            processInstruction(instruction);
        }while(!instruction.equals("x"));
        System.out.println("-------- Permainan Selesai --------");
    }

    private String waitInstruction() {
        System.out.println("-------- Instruksi --------");
        System.out.println("Instruksi: {d=kanan/a=kiri/w=atas/s=bawah}{jumlah langkah}");
        System.out.println("Contoh: w3 berarti 'keatas 3 langkah'");
        System.out.print("Masukan instruksi: ");
        return scanner.nextLine();
    }

    private void processInstruction(String instruction) {
        char direction = instruction.charAt(0);
        int steps;
        try {
            steps = Integer.parseInt(instruction.substring(1));
        } catch (NumberFormatException e) {
            System.out.println("Format instruksi salah. Harus berupa huruf diikuti angka.");
            return;
        }

        switch (direction) {
            case 'w':
                moveUp(steps);
                break;
            case 's':
                moveDown(steps);
                break;
            case 'a':
                moveLeft(steps);
                break;
            case 'd':
                moveRight(steps);
                break;
            default:
                System.out.println("Arah tidak dikenali. Gunakan w, a, s, atau d.");
        }
    }

    private void moveUp(int steps) {
        if (robot.getPosition().getY() - steps >= 0) {
            robot.getPosition().setY(robot.getPosition().getY() - steps);
        } else {
            System.out.println("Gerakan tidak valid: Robot akan keluar area.");
        }
    }

    private void moveDown(int steps) {
        if (robot.getPosition().getY() + steps < layout.getMaxY()) {
            robot.getPosition().setY(robot.getPosition().getY() + steps);
        } else {
            System.out.println("Gerakan tidak valid: Robot akan keluar area.");
        }
    }

    private void moveLeft(int steps) {
        if (robot.getPosition().getX() - steps >= 0) {
            robot.getPosition().setX(robot.getPosition().getX() - steps);
        } else {
            System.out.println("Gerakan tidak valid: Robot akan keluar area.");
        }
    }

    private void moveRight(int steps) {
        if (robot.getPosition().getX() + steps < layout.getMaxX()) {
            robot.getPosition().setX(robot.getPosition().getX() + steps);
        } else {
            System.out.println("Gerakan tidak valid: Robot akan keluar area.");
        }
    }
    
    private void draw() {
        System.out.println("------ Posisi Terbaru ------");
        layout.updateRobotPosition(robot.getPosition().getX(), robot.getPosition().getY(), robot.getIcon());
        layout.draw();
        /*
        Gambar layout:
        Contoh:
        - Posisi robot di 1,1
        - Area permainan luasnya 10,10
        sehingga gambar layout akan menjadi:

            o*********
            **********
            **********
            **********
            **********
            **********
            **********
            **********
            **********
            **********

            - konfigurasi (icon robot, posisi robot, luas area dan icon area permainan yang tidak ditempati robot) silahkan gunakan prinsip OOP
            - icon cukup menggunakan karakter yang ada di keyboard.
         */

    }
}