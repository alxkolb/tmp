import java.util.*;

public class HashCrack {
    // Хеши Ильи
    static int hash1(int key){
        // ((key << 2)**2) ^ 1537
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
    static HashMap<Integer, Set<Integer>> search2(int minKey, int maxKey){
        /**
         *   Поиск коллизий в hash2 в диапазоне от minKey до maxKey
         *
         *   В HashMap ключ -- хеш-сумма, а в Set сохраняются все найденные коллизии,
         * удовлетворяющие этой хеш-сумме
         */
        //
        HashMap<Integer, Set<Integer>> hashes =
                new HashMap<>();

        for (int i = minKey; i <= (maxKey - 1); i++){
            int hash_i = hash2(i);
            for (int j = i + 1; j <= maxKey; j++)
                if (i != j && hash_i == hash2(j)){
                    // Извлекаем Set для его дальнейшего изменения
                    Set<Integer> keys =
                            hashes.remove(hash2(i));
                    if (keys == null)
                        keys = new HashSet<>();
                    // Добавляем найденные коллизии
                    keys.add(i);
                    keys.add(j);
                    // И связываем их с хеш-суммой
                    hashes.put(hash_i, keys);
                }
        }
        return hashes;
    }

    public static void main(String[] args) {
        System.out.println("hash1:\n" + hash1(1234));
        System.out.println(crack1(hash1(1234)));

        System.out.println("\nhash2:\n" + hash2(1234));
        System.out.println(crack2(hash2(1234),0, 1000));

        System.out.println("\ncollisions in hash2 (hash = [key1, key2...]):\n" +
                search2(0, 100));
    }
}
