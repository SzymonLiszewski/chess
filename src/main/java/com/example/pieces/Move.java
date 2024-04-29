package com.example.pieces;

public class Move {
    Game.squares source;
    Game.squares target;
    Game.pieces piece;
    Game.color color;
    Game.pieces promotedPiece;

    public Move(Game.squares source, Game.squares target, Game.pieces piece, Game.color color) {
        this.source = source;
        this.target = target;
        this.piece = piece;
        this.color = color;
    }

    public Move(Game.squares source, Game.squares target, Game.pieces piece, Game.pieces promotedPiece, Game.color color) {
        this.source = source;
        this.target = target;
        this.piece = piece;
        this.promotedPiece = promotedPiece;
        this.color = color;
    }

    public Game.squares getSource() {
        return source;
    }

    public void setSource(Game.squares source) {
        this.source = source;
    }

    public Game.squares getTarget() {
        return target;
    }

    public void setTarget(Game.squares target) {
        this.target = target;
    }

    public Game.pieces getPiece() {
        return piece;
    }

    public void setPiece(Game.pieces piece) {
        this.piece = piece;
    }

    public Game.pieces getPromotedPiece() {
        return promotedPiece;
    }

    public void setPromotedPiece(Game.pieces promotedPiece) {
        this.promotedPiece = promotedPiece;
    }

    @Override
    public String toString() {
        return source.toString() + target.toString();
    }

    public Game.color getColor() {
        return color;
    }

    public void setColor(Game.color color) {
        this.color = color;
    }
}
