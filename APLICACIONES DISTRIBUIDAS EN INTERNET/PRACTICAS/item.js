class Item{
    constructor(name)
    {
        this.name = name;
    }
    toString()
    {
        return "Soy el método toString() del item " + this.name;
    }
} 

module.exports = Item;