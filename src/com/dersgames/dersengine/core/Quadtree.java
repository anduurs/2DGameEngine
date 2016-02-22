package com.dersgames.dersengine.core;

import java.util.ArrayList;
import java.util.List;

import com.dersgames.dersengine.components.BoundingBox;

public class Quadtree {

	private int MAX_OBJECTS = 10;
	private int MAX_LEVELS = 5;

	private int level;
	private List<BoundingBox> objects;
	private BoundingBox bounds;
	private Quadtree[] nodes;

	public Quadtree(int pLevel, BoundingBox pBounds) {
		level = pLevel;
		objects = new ArrayList<BoundingBox>();
		bounds = pBounds;
		nodes = new Quadtree[4];
	}

	/*
	 * Clears the quadtree
	 */
	public void clear() {
		objects.clear();

		for (int i = 0; i < nodes.length; i++) {
			if (nodes[i] != null) {
				nodes[i].clear();
				nodes[i] = null;
			}
		}
	}

	/*
	 * Splits the node into 4 subnodes
	 */
	private void split() {
		int subWidth = (int) (bounds.width / 2);
		int subHeight = (int) (bounds.height / 2);
		int x = (int) bounds.x;
		int y = (int) bounds.y;

		nodes[0] = new Quadtree(level + 1, new BoundingBox(x + subWidth, y, subWidth, subHeight));
		nodes[1] = new Quadtree(level + 1, new BoundingBox(x, y, subWidth, subHeight));
		nodes[2] = new Quadtree(level + 1, new BoundingBox(x, y + subHeight, subWidth, subHeight));
		nodes[3] = new Quadtree(level + 1, new BoundingBox(x + subWidth, y + subHeight, subWidth, subHeight));
	}

	/*
	 * Determine which node the object belongs to. -1 means object cannot
	 * completely fit within a child node and is part of the parent node
	 */
	private int getIndex(BoundingBox pRect) {
		int index = -1;
		double verticalMidpoint = bounds.getX() + (bounds.getWidth() / 2);
		double horizontalMidpoint = bounds.getY() + (bounds.getHeight() / 2);

		// Object can completely fit within the top quadrants
		boolean topQuadrant = (pRect.getY() < horizontalMidpoint
				&& pRect.getY() + pRect.getHeight() < horizontalMidpoint);
		// Object can completely fit within the bottom quadrants
		boolean bottomQuadrant = (pRect.getY() > horizontalMidpoint);

		// Object can completely fit within the left quadrants
		if (pRect.getX() < verticalMidpoint && pRect.getX() + pRect.getWidth() < verticalMidpoint) {
			if (topQuadrant) {
				index = 1;
			} else if (bottomQuadrant) {
				index = 2;
			}
		}
		// Object can completely fit within the right quadrants
		else if (pRect.getX() > verticalMidpoint) {
			if (topQuadrant) {
				index = 0;
			} else if (bottomQuadrant) {
				index = 3;
			}
		}

		return index;
	}

	/*
	 * Insert the object into the quadtree. If the node exceeds the capacity, it
	 * will split and add all objects to their corresponding nodes.
	 */
	public void insert(BoundingBox pRect) {
		if (nodes[0] != null) {
			int index = getIndex(pRect);

			if (index != -1) {
				nodes[index].insert(pRect);

				return;
			}
		}

		objects.add(pRect);

		if (objects.size() > MAX_OBJECTS && level < MAX_LEVELS) {
			if (nodes[0] == null) {
				split();
			}

			int i = 0;
			while (i < objects.size()) {
				int index = getIndex(objects.get(i));
				if (index != -1) {
					nodes[index].insert(objects.remove(i));
				} else {
					i++;
				}
			}
		}
	}

	/*
	 * Return all objects that could collide with the given object
	 */
	public List<BoundingBox> retrieve(List<BoundingBox> returnObjects, BoundingBox pRect) {
		int index = getIndex(pRect);
		if (index != -1 && nodes[0] != null) {
			nodes[index].retrieve(returnObjects, pRect);
		}

		returnObjects.addAll(objects);

		return returnObjects;
	}

}
