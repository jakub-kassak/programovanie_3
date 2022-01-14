package FSBT;

import java.util.Objects;
import java.util.Optional;
import java.util.function.Function;

public interface U1 {
    Function<FBST, Integer> size = fbst ->
            fbst == null
                ? 0
                : 1 + U1.size.apply(fbst.left()) + U1.size.apply(fbst.right());

    Function<FBST, Function<Integer, FBST>> add = fbst -> val ->
            fbst == null
                    ? new FBST(val, null, null)
                    : fbst.value().equals(val)
                        ? fbst
                        : fbst.value() < val
                            ? new FBST(fbst.value(), fbst.left(), U1.add.apply(fbst.right()).apply(val))
                            : new FBST(fbst.value(), U1.add.apply(fbst.left()).apply(val), fbst.right());

    Function<FBST, Function<Integer, Boolean>> contains = fbst -> val ->
            Optional.ofNullable(fbst)
                    .map(fbst2 -> Objects.equals(fbst2.value(), val)
                            || U1.contains.apply(fbst2.left()).apply(val)
                            || U1.contains.apply(fbst2.right()).apply(val))
                    .orElse(false);

    Function<FBST, FBST> popMin = fbst ->
            fbst == null
                ? null
                : fbst.left() == null
                    ? fbst.right()
                    : new FBST(fbst.value(), U1.popMin.apply(fbst.left()), fbst.right());

    Function<FBST, Integer> min = fbst ->
            fbst == null
                ? null
                : fbst.left() == null
                    ? fbst.value()
                    : U1.min.apply(fbst.left());

    Function<FBST, Function<Integer, FBST>> remove = fbst -> val ->
        fbst == null
            ? null
            : fbst.value().equals(val)
                ? fbst.right() == null
                    ? fbst.left()
                    : new FBST(U1.min.apply(fbst.right()), fbst.left(), U1.popMin.apply(fbst.right()))
                : fbst.value() < val
                    ? new FBST(fbst.value(), fbst.left(), U1.remove.apply(fbst.right()).apply(val))
                    : new FBST(fbst.value(), U1.remove.apply(fbst.left()).apply(val), fbst.right());
//    {
//        if (fbst == null)
//            return null;
//        if (fbst.value().equals(val)){
//            if (fbst.left() == null)
//                return fbst.right();
//            if (fbst.right() == null)
//                return fbst.left();
//            return new FSBT.FBST(FSBT.U1.min.apply(fbst.right()), fbst.left(), FSBT.U1.popMin.apply(fbst.right()));
//        }
//        if (fbst.value() < val)
//            return new FSBT.FBST(fbst.value(), fbst.left(), FSBT.U1.remove.apply(fbst.right()).apply(val));
//        return new FSBT.FBST(fbst.value(), FSBT.U1.remove.apply(fbst.left()).apply(val), fbst.right());
//    };
//            fbst == null
//                ? null
//                : fbst.value().equals(val)
//                    ? new FSBT.FBST(FSBT.U1.min.apply(fbst), FSBT.U1.popMin.apply(fbst.left()), fbst.right())
//                    : fbst.value() < val
//                        ? new FSBT.FBST(fbst.value(), FSBT.U1.remove.apply(fbst.left()).apply(val), fbst.right())
//                        : new FSBT.FBST(fbst.value(), fbst.left(), FSBT.U1.remove.apply(fbst.right()).apply(val));

}
