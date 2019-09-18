import java.util.Scanner;

public class HashCrack {
    // Хеши Ильи
    static int hash1(int key){
        // ((key << 2) **2) ^ 1537
        return (int) Math.pow(key << 2, 2) ^ 1537;
    }
    static int hash2(int key){
        // ((key + 5)**3) % 1000
        return (int) Math.pow(key + 5, 3) % 1000;
    }
    // Их взлом
    static int crack1(int hash){
        int key;
        key = hash ^ 1537;
        key = (int) Math.sqrt(key);
        key = key >> 2;
        return key;
    }
    static Integer crack2(int hash, int minKey, int maxKey){
        for (int key = minKey; key < maxKey; key++ )
            if (hash2(key) == hash)
                return key;
        return null;
    }
    static void search2(int minKey, int maxKey){
        // TODO: ключ - значения
        for (int i = minKey; i <= (maxKey - 1); i++)
            for (int j = i + 1; j <= maxKey; j++)
                if (i != j && hash2(i) == hash2(j))
                    System.out.printf("keys: %d, %d; hash: %d\n", i, j, hash2(i));
    }
    public static void main(String[] args) {
        System.out.println("hash1:\n" + hash1(1234));
        System.out.println(crack1(hash1(1234)));

        System.out.println("\nhash2:\n" + hash2(1234));
        System.out.println(crack2(hash2(1234),0, 1000));

        System.out.print("\nВведите maxKey: ");
        search2(0, new Scanner(System.in).nextInt());
    }
}
