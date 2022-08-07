package search

interface SymbolTable<Key: Comparable<Key>, Value> {
    fun put(key: Key, value: Value);
    fun get(key: Key): Value?;
    fun containes(key: Key): Boolean;
    fun delete(key: Key);
    fun isEmpty(): Boolean;
    // TODO: support iterable interface
}