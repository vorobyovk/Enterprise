package mod1_collection_effectiveness;

public class CollectionEffectiveness {
    public static void main(String[] args) {
        TestCollections testCollections = new TestCollections();

        testCollections.add();
        testCollections.get();
        testCollections.remove();
        testCollections.contains();
        testCollections.populate();
        testCollections.iteratorAdd();
        testCollections.iteratorRemove();

        testCollections.printResults();
    }
}