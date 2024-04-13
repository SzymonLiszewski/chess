package com.example;

import com.example.pieces.Piece;
import com.example.pieces.Position;

public class Move {
    private Piece piece;
    private Position position;

    public Move(Piece piece, Position position) {
        this.piece = piece;
        this.position = position;
    }

    public Piece getPiece() {
        return piece;
    }

    public void setPiece(Piece piece) {
        this.piece = piece;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }
}
