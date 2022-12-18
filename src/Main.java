import java.util.Scanner;

public class Main
{
    public static int oyunsirasi = 1; // X'i temsil eder.
    public static Kutu[][] kutular = new Kutu[3][3];

    public static void main(String[] args)
    {
        Scanner scanner = new Scanner(System.in);
        kutulariAta();
        do {
            oyunTahtasi(kutular);
            if (oyunsirasi == 1) {
                System.out.println("X için satır giriniz.");
            } else {
                System.out.println("O için satır giriniz.");
            }
            int x = scanner.nextInt();
            if (oyunsirasi == 1) {
                System.out.println("X için sütun giriniz.");
            } else {
                System.out.println("O için sütun giriniz.");
            }
            int y = scanner.nextInt();

            if (tahminGecerliMi(x,y))
            {
                oyna(x,y);
            }
            else{
                System.out.println("Sadece 0 ve 2 arasında index girebilirsiniz. Lütfen tekrar deneyin.");
            }
        }while (!oyunBittiMi());

        oyunTahtasi(kutular);
        if (berabereMi())
        {
            System.out.println("Oyun bitti. Sonuç berabere.");
        }
        else
        {
            if (oyunsirasi == 1)
            {
                System.out.println("Oyun bitti. 'O' Kazandı.");
            }
            else{
                System.out.println("Oyun bitti. 'X' Kazandı.");
            }
        }


    }

    public static void oyunTahtasi(Kutu[][] kutular)
    {
        for (int i = 0; i < kutular.length; i++) {
            System.out.print("|");
            for (int j = 0; j < kutular.length; j++) {
                if (kutular[i][j].isOynandi())
                {
                    System.out.print(kutular[i][j].getDeger() + "|");
                }
                else
                {
                    System.out.print(" |");
                }
            }
            System.out.println();
        }
    }

    public static void oyna(int x, int y)
    {
        if (!kutular[x][y].isOynandi())
        {
            kutular[x][y].setOynandi(true);
            if (oyunsirasi == 1)
            {
                oyunsirasi = 2;
                kutular[x][y].setDeger("X");
            }
            else
            {
                oyunsirasi = 1;
                kutular[x][y].setDeger("O");
            }
        }
        else
        {
            System.out.println("Zaten oynanmış bir indexi oynadınız. Lütfen tekrar deneyiniz.");
        }
    }

    public static boolean satirKontrol()
    {
        int sayi = 0;
        for (int i = 0; i < kutular.length; i++) {
            for (int j = 0; j < kutular.length; j++) {
                if (oyunsirasi == 2) //oynadıktan sonra sıra karşıya geçtiği için x için 2 y için 1 veriyorum.
                {
                     if (kutular[i][j].getDeger().equals("X"))
                     {
                         sayi++;
                     }
                }
                else
                {
                    if (kutular[i][j].getDeger().equals("O"))
                    {
                        sayi++;
                    }
                }
            }
            if (sayi == 3)
            {
                break;
            }
            else{
                sayi = 0;
            }
        }

        return sayi == 3;
    }

    public static boolean sutunKontrol()
    {
        int sayi = 0;
        for (int i = 0; i < kutular.length; i++) {
            for (int j = 0; j < kutular.length; j++) {
                if (oyunsirasi == 2) //oynadıktan sonra sıra karşıya geçtiği için x için 2 y için 1 veriyorum.
                {
                    if (kutular[j][i].getDeger().equals("X"))
                    {
                        sayi++;
                    }
                }
                else
                {
                    if (kutular[j][i].getDeger().equals("O"))
                    {
                        sayi++;
                    }
                }
            }
            if (sayi == 3)
            {
                break;
            }
            else{
                sayi = 0;
            }
        }
        return sayi == 3;
    }

    public static boolean kosegenKontrol()
    {
        int sayi = 0;
        for (int i = 0; i < kutular.length; i++) {
            for (int j = 0; j < kutular.length; j++) {
                if (oyunsirasi == 2) //oynadıktan sonra sıra karşıya geçtiği için x için 2 y için 1 veriyorum.
                {
                   if (i == j)
                   {
                       if (kutular[i][j].getDeger().equals("X"))
                       {
                           sayi++;
                       }
                   }
                }
                else
                {
                    if (i == j)
                    {
                        if (kutular[i][j].getDeger().equals("O"))
                        {
                            sayi++;
                        }
                    }
                }
            }
        }
        return sayi == 3;
    }

    public static boolean tersKosegenKontrol()
    {
        String toplam = kutular[0][2].getDeger() + kutular[1][1].getDeger() + kutular[2][0].getDeger();
        if (toplam.equals("XXX") || toplam.equals("OOO")){
            return true;
        }
        else{
            return false;
        }
    }

    public static boolean berabereMi()
    {
        int sayi = 0;
        for (int i = 0; i < kutular.length; i++) {
            for (int j = 0; j < kutular.length; j++) {
                if (kutular[i][j].getDeger().equals("X") || kutular[i][j].getDeger().equals("O"))
                {
                   sayi++;
                }
            }
        }
        return sayi == 9;
    }

    public static boolean oyunBittiMi()
    {
        return satirKontrol() || sutunKontrol() || kosegenKontrol() || berabereMi() || tersKosegenKontrol();
    }

    public static void kutulariAta()
    {
        for (int i = 0; i < kutular.length; i++) {
            for (int j = 0; j < kutular.length; j++) {
                kutular[i][j] = new Kutu();
            }
        }
    }

    public static boolean tahminGecerliMi(int x, int y)
    {
        return (x >= 0 && x <= 2) && (y >= 0 && y <= 2);
    }
}
