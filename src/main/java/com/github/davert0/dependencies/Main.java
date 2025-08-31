package com.github.davert0.dependencies;


// 1
class Animal {
    public void makeGenericSound() {
        System.out.println("Some generic animal sound");
    }
}

class Cat extends Animal {
    // Переопределение метода makeSound
    public void makeSound() {
        System.out.println("Meow");
    }
}

public class Main {
    public static void main(String[] args) {
        Animal myCat = new Cat();
        myCat.makeSound();  // Ошибка компиляции
    }
}

// 2

class Animal {
    public void makeSound() {
        System.out.println("Some generic animal sound");
    }
}

class Cat extends Animal {
    @Override
    public void makeSound(int numberOfSounds) { // ошибка компиляции
        for (int i = 0; i < numberOfSounds; i++) {
            System.out.println("Meow");
        }
    }
    
    @Override
    public void makeSound() {
        System.out.println("Meow");
    }
}

public class Main {
    public static void main(String[] args) {
        Animal cat = new Cat();
        cat.makeSound();
        cat.makeSound(3);
    }
}


// 3

// Две разные версии одной зависимости, которые тянут за собой транзитивные зависимости
