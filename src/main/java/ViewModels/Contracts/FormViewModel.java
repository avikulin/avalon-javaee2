package ViewModels.Contracts;

import Common.Exceptions.DataValidationException;

public interface FormViewModel<T, K> {
    T getData(K id) throws DataValidationException;

    void putData(T item) throws DataValidationException;

    void deleteItem(K id) throws DataValidationException;
}
