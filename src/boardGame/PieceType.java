package boardGame;

public enum PieceType {
    ELIXIR {
        @Override
        public Piece newInstance() {
            return null;
        }
    },
    MONSTER {
        @Override
        public Piece newInstance() {
            return null;
        }
    },
    VARIABLE_TRAP {
        @Override
        public Piece newInstance() {
            return null;
        }
    },
    FIXED_TRAP {
        @Override
        public Piece newInstance() {
            return null;
        }
    },
    BOSS {
        @Override
        public Piece newInstance() {
            return null;
        }
    };

     public abstract Piece newInstance();
}
