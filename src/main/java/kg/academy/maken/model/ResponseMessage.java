package kg.academy.maken.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ResponseMessage<T> {
    private T value;
    private String message;

    public ResponseMessage<T> prepareSuccessMessage(T value) {
        return ResponseMessage.<T>builder()
                .value(value)
                .message(null)
                .build();
    }

    public ResponseMessage<T> prepareFailMessage(String message) {
        return ResponseMessage.<T>builder()
                .value(null)
                .message(message)
                .build();
    }
}
