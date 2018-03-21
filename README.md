# ddd-defined
def-undefパターン用ライブラリ

## 機能
def-undefパターンを作る際に必要なEitherを生成する
Eitherはvavrとjavaslangで生成できる

## 使い方
### build.gradleの設定
#### vavrの場合
```gradle
repositories {
    ...
    maven { url 'https://naosim.github.io/ddd-defined/' }
}

dependencies {
    ...
    compile group:'com.naosim.ddd.defined', name:'definedforvavr', version:'0.1.4'

}
```

#### javaslangの場合
```gradle
repositories {
    ...
    maven { url 'https://naosim.github.io/ddd-defined/' }
}

dependencies {
    ...
    compile group:'com.naosim.ddd.defined', name:'definedforslang', version:'0.1.4'

}
```

### 実装例
```java
public class Main {
    static final DefinedOrUnDefinedFactory<PanUndefined, PanDefined> factory
            = new DefinedOrUnDefinedFactory<>(
                    PanUndefined::new, PanDefined.values(), PanDefined::name
            );

    public static void main(String... args) {
        Either<PanUndefined, PanDefined> act1 = factory.create("食パン");
        Either<PanUndefined, PanDefined> act2 = factory.create("メロンパン");

        System.out.println(act1);// Right(PanDefined(食パン))
        System.out.println(act2);// Left(PanUndefined(メロンパン))
    }

    public enum PanDefined {
        アンパン, 食パン, カレーパン;

        @Override
        public String toString() {
            return createString(this, name());
        }
    }

    public static class PanUndefined {
        private final String value;

        public PanUndefined(String value) {
            this.value = value;
        }
        @Override
        public String toString() {
            return createString(this, value);
        }
    }

    // toString用
    public static String createString(Object target, String... value) {
        return String.format(
                "%s(%s)",
                target.getClass().getSimpleName(),
                Stream.of(value).collect(Collectors.joining(", "))
        );
    }

}
```
