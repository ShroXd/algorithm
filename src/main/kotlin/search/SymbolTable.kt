package search

// TODO: make it generic
interface SymbolTable {
    fun put(key: String, value: String);
    fun get(key: String): String;
    fun containes(key: String): Boolean;
    fun delete(key: String);
    fun isEmpty(): Boolean;
    // TODO: support iterable interface
}