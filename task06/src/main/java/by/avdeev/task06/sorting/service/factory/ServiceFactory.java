package by.avdeev.task06.sorting.service.factory;

import by.avdeev.task06.sorting.service.SortingService;
import by.avdeev.task06.sorting.service.impl.BinaryInsertSorting;
import by.avdeev.task06.sorting.service.impl.BubbleSorting;
import by.avdeev.task06.sorting.service.impl.DoubleSelectionSorting;
import by.avdeev.task06.sorting.service.impl.InsertSorting;
import by.avdeev.task06.sorting.service.impl.SelectionSorting;
import by.avdeev.task06.sorting.service.impl.ShakerSorting;

public class ServiceFactory {
    private static ServiceFactory instance = new ServiceFactory();
    private final SortingService selectionSorting = new SelectionSorting();
    private final SortingService doubleSelectionSorting = new DoubleSelectionSorting();
    private final SortingService insertSorting = new InsertSorting();
    private final SortingService binaryInsertSorting = new BinaryInsertSorting();
    private final SortingService shakerSorting = new ShakerSorting();
    private final SortingService bubbleSorting = new BubbleSorting();

    public static ServiceFactory getInstance() {
        return instance;
    }

    public SortingService getSelectionSortingService() {
        return selectionSorting;
    }

    public SortingService getDoubleSelectionSorting() {
        return doubleSelectionSorting;
    }

    public SortingService getInsertSorting() {
        return insertSorting;
    }

    public SortingService getBinaryInsertSorting() {
        return binaryInsertSorting;
    }

    public SortingService getShakerSorting() {
        return shakerSorting;
    }

    public SortingService getBubbleSorting() {
        return bubbleSorting;
    }
}
