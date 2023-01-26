package mapdb.example.vrc;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;
import com.esotericsoftware.kryo.serializers.FieldSerializer;
import org.jetbrains.annotations.NotNull;
import org.mapdb.DataInput2;
import org.mapdb.DataOutput2;
import org.mapdb.Serializer;

import java.io.IOException;

public class DBMakerSerializer<E> implements Serializer<E> {

    final private Kryo kryo = new Kryo();

    public DBMakerSerializer() {
        kryo.setDefaultSerializer(FieldSerializer.class);
        kryo.setRegistrationRequired(false);
    }

    @Override
    public void serialize(@NotNull DataOutput2 out, @NotNull E value) {
        try(final Output output = new Output(out)) {
            kryo.writeClassAndObject(output,value);
        }
    }

    @Override
    public E deserialize(@NotNull DataInput2 input, int available) {
        try{
            final byte[] bytes = new byte[available];
            input.readFully(bytes);
            Input kryoInput = new Input(bytes,0,available);
            return (E) kryo.readClassAndObject(kryoInput);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
