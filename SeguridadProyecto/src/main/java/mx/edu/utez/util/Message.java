package mx.edu.utez.util;

import lombok.*;

@Data
@RequiredArgsConstructor
@AllArgsConstructor
@NoArgsConstructor
public class Message {
    @NonNull
    private String text;
    @NonNull
    private String type;
    private Object result;
}