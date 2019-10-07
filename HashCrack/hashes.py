def hash1 (key):
    key *= key;
    key += 1;
    return key % 1000

def hash2 (key):
    result = 100
    for i in range(50):
        result += key % (i + 1)
    return result % 1000
