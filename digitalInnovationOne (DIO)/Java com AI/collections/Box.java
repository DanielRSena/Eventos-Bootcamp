package collections;

public class Box<T> {
    //Object obj;

    private T t;

    public void setT(T t) {
        this.t = t;
    }

    public T getT() {
        return t;
    }
}

//Esse 't' pode ser qualquer coisa, como um objeto, uma string, uma classe, etc. Só não pode se referir a um tipo primitivo. Logo, 'T' é um genérico que representa Type.

/*
Nome dos parâmetros mais usados além do 't':
- E: Element
- T: Type
- K: Key
- V: Value
- N: Number
- S: String
*/