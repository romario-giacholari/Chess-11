package com.mygdx.game.views;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

public class BlackKnight extends PieceSprite{

	
	private Texture texture = new Texture(Gdx.files.internal("blackKnight.png"));

	public BlackKnight() {
		super();
		sprite = texture;	
		// TODO Auto-generated constructor stub
	}

}