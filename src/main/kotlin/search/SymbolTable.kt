package search

interface SymbolTable<Key, Value> {
    fun put(key: Key, value: Value);
    fun containes(key: Key): Boolean;
    fun get(key: Key): Value;
    fun delete(key: Key);
    fun isEmpty(): Boolean;
    // TODO: support iterable interface
}