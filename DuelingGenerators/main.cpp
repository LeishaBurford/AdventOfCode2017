//
//  main.cpp
//  DuelingGenerators
//
//  Created by Leisha Burford on 2017-12-15.
//  Copyright © 2017 Leisha Burford. All rights reserved.
//

#include <iostream>
const int divisor = 2147483647;
struct Generator {
    long current = 0;
    int factor = 0;
    Generator(int x, int y) {
        current = (long)x;
        factor = y;
    }
    long nextValue() {
        current = (current * factor) % divisor;
        return current;
    }
};
int main(int argc, const char * argv[]) {
    int a_initial = 699;
    int b_initial = 124;
    int a_gen = 16807;
    int b_gen = 48271;
    Generator a = Generator(a_initial, a_gen);
    Generator b = Generator(b_initial, b_gen);
    int count = 0;
    for(int i = 0; i < 40000000; i++) {
        std::bitset<32> A (a.nextValue());
        std::bitset<32> B (b.nextValue());
        bool match = true;
        for(int j = 0; j < 16; j++) {
            if(A[j] != B[j]) {
                match = false;
                break;
            }
        }
        count += (match) ? 1 : 0;
    }
    
    std::cout << count << std::endl;
    
    return 0;
}


