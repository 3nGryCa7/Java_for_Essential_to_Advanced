public class InheritanceExample {

    public class Animal {

        // public: 全部都可以存取
        // protected: 同一個 package 或是不同 package 的子類別可以存取
        // default: 同一個 package 可以存取
        // private: 只有同一個 class 可以存取

        private String name;

        public Animal(String name) {
            this.name = name;
        }

        public String getName() {
            return this.name;
        }

        public void eat() {
            System.out.println(this.getName() + " eat");
        }

        // Overload
        public void eat(String food) {
            System.out.println(this.getName() + " eat " + food);
        }
    }

    public class Dog extends Animal {

        // Override: 子類別可以覆寫父類別的方法
        // Overload: 同一個類別可以有多個同名的方法，但是參數個數或是型別不同
        // super: 子類別可以使用 super 關鍵字存取父類別的屬性或是方法

        public Dog(String name) {
            super(name);
        }

        // Override
        @Override
        public void eat() {
            System.out.println(this.getName() + " eat bone");
        }
    }
    

    public static void main(String[] args) {

        InheritanceExample example = new InheritanceExample();
        InheritanceExample.Animal animal = example.new Animal("Animal");
        Dog dog = example.new Dog("Dog");

        animal.eat(); 
        dog.eat();  // Override in Dog
        dog.eat("meat");    // Overload in Animal
    }
}
