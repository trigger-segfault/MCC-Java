/*
 * Class Name: HelloWorldDivergence
 * Author: Robert Jordan
 * Date Created: May 3, 2019
 * Synopsis: A Screen for timing and drawing fancy Hello World divergence
 *           animations.
 */
package trigger.finalproject.projects.helloworld;

import java.util.ArrayList;
import java.util.Random;
import trigger.finalproject.utilities.*;

/**
 * A Screen for timing and drawing fancy Hello World divergence animations.
 */
public class HelloWorldDivergence extends HelloWorldDrawMenu {
	// <editor-fold defaultstate="expanded" desc="Constructors">
	/**
	 * Constructs the Hello World Menu with the specified text file path to
	 * display.
	 * @param textFile The path to the text file to print.
	 * @param secondTextFile The second text file needed by the menu.
	 */
	public HelloWorldDivergence(String textFile, String secondTextFile) {
		super(textFile, secondTextFile);
	}
	// </editor-fold>
	
	// <editor-fold defaultstate="expanded" desc="Classes">
	public static final String TEXT = "HELLOWORLD!";
	public static final int LENGTH = 11;
	private class HelloPoint {
		
		public HelloPoint() {
			index = -1;
			index = 0;
			string = String.valueOf(TEXT.charAt(index));
			offset = CENTER;
			distance = 0;
			branches = 0;
		}
		
		public HelloPoint(int distance, HelloPoint seed) {
			this.index = seed.index;
			this.string = seed.string;
			this.offset = seed.offset + distance;
			this.branches = 0;
			this.distance = distance;
			seed.branches++;
			
			StringBuilder line = new StringBuilder();
			for (int i = 0; i < Math.abs(distance); i++) {
				next(false);
				if (distance > 0)
					line.append(this.string);
				else
					line.insert(0, this.string);
			}
			this.string = line.toString();
		}
		
		public String string;
		public int index;
		public int offset;
		public int distance;
		public int branches;
		
		public final void next(boolean resetDistance) {
			index = (index + 1) % LENGTH;
			string = String.valueOf(TEXT.charAt(index));
			if (resetDistance)
				distance = 0;
			//distance = 0;
			/*if (distance > 0)
				distance--;
			else if (distance < 0)
				distance++;*/
		}
	}
	// </editor-fold>
	// <editor-fold defaultstate="expanded" desc="Fields">
	private static final int CENTER = WIDTH / 2;
	private static final int MAX_POINTS = 8;
	private static final int MIN_SPACE = 4;
	private static final int MARGIN = 6;
	private static final int MARGIN_HALF = MARGIN / 2;
	private static final int MIN = MARGIN;
	private static final int MAX = WIDTH - MARGIN;
	private static final float WEIGHT_EXP = 0.6f;
	
	private ArrayList<HelloPoint> points = new ArrayList<>();
	private int divergeCooldown = -10;
	private boolean converging = false;
	private int ticks = 0;
	private boolean canDiverge = true;
	private static final Random random = new Random();
	private static final int MS_PER_TICK = 16 * 2;
	// </editor-fold>
			
	private void printPoints() {
		StringBuilder str = new StringBuilder();
		for (int i = 0; i < points.size(); i++) {
			HelloPoint point = points.get(i);
			if (point.distance > 0) {
				str.append(point.string);
			}
			else {
				str.append(StringUtils.repeat(' ', point.offset - str.length()));
				str.append(point.string);
			}
			
			point.next(true);
			
		}
		Console.printLine(str.toString());
	}
	
	private void tick() throws Exception {
		divergeCooldown++;
		if (canDiverge && divergeCooldown >= 0 && random.nextInt(20) <= divergeCooldown) {
			randomDiverge();
		}
		printPoints();
		
		// Require we haven't just diverged and also make sure we have diverged a few times.
		// More points means higher chance of diverging
		if (random.nextInt(800) <= ticks + points.size() * 4 &&
			divergeCooldown > 0 && (points.size() >= 5 || !canDiverge))
		{
			converging = true;
		}
		if (ticks == 0)
			Thread.sleep(500);
		ticks++;
		Thread.sleep(MS_PER_TICK);
	}
	
	private void randomDiverge() {
		int[] spacing = new int[points.size() * 2];
		int lastIndex = 0;
		float[] weights = new float[points.size() * 2];
		
		float total = 0;
		for (int i = 0; i < points.size(); i++) {
			HelloPoint point = points.get(i);
			int left, right;
			if (i == 0)
				left  = point.offset - MIN;
			else
				left  = point.offset - points.get(i-1).offset;
			
			if (i + 1 == points.size())
				right = MAX - point.offset;
			else
				right = points.get(i+1).offset - point.offset;
			
			if (left  >= MIN_SPACE) {
				spacing[i*2+0] = left;
				float weight = (float) left  * (1 + left  * WEIGHT_EXP);
				weights[i*2+0] = weight;
				lastIndex = i*2+0;
				total += weight;
			}
			if (right >= MIN_SPACE) {
				spacing[i*2+1] = right;
				float weight = (float) right * (1 + right * WEIGHT_EXP);
				weights[i*2+1] = weight;
				lastIndex = i*2+1;
				total += weight;
			}
		}
		
		// We have no room to diverge
		if (total == 0) {
			canDiverge = false;
			return;
		}
		
		boolean leftSide = false;
		HelloPoint seedPoint = null;
		int space = 0;
		int seedIndex = 0;
		
		float roll = random.nextFloat() * total;
		//int roll = random.nextInt(total);
		for (int i = 0; i < points.size(); i++) {
			seedIndex = i;
			HelloPoint point = points.get(i);
			float left  = weights[i*2+0];
			float right = weights[i*2+1];
			if (roll < left || i*2+0 == lastIndex) {
				leftSide = true;
				seedPoint = point;
				space = spacing[i*2+0];
				break;
			}
			roll -= left;
			if (roll < right || i*2+1 == lastIndex) {
				leftSide = false;
				seedPoint = point;
				space = spacing[i*2+1];
				break;
			}
			roll -= right;
		}
		int distance = 2 + random.nextInt(space - 2 - 2 + 1);
		if (leftSide) {
			points.add(seedIndex, new HelloPoint(-distance, seedPoint));
		}
		else {
			points.add(seedIndex+1, new HelloPoint(distance, seedPoint));
		}
		divergeCooldown = -2 - random.nextInt(6);
		canDiverge = points.size() < MAX_POINTS;
	}
			
	// <editor-fold defaultstate="expanded" desc="Draw Override">
	@Override
	protected void draw() throws Exception {
		String[] mask = FileUtils.readAllLines(secondTextFile);
		for (int i = 0; i < mask.length; i++) {
			String line = mask[i];
			if (!line.isEmpty())
				line = StringUtils.center(line, WIDTH);
			//line = StringUtils.padRight(line, WIDTH);
			mask[i] = line;
		}
		
		points.clear();
		divergeCooldown = -10;
		converging = false;
		ticks = 0;
		canDiverge = true;
		points.add(new HelloPoint());
		
		// Keep ticking and diverging
		while (!converging) {
			tick();
		}
		
		// Output the base of the lines spreading into one.
		int index = random.nextInt(LENGTH);
		StringBuilder str = new StringBuilder();
		for (int i = 0; i < points.size(); i++) {
			HelloPoint point = points.get(i);
			if (str.length() < point.offset - 2)
				str.append(StringUtils.repeat(' ', point.offset - 2 - str.length()));
			int start = Math.max(str.length() - (point.offset - 2), 0);
			for (int j = start; j < 5; j++) {
				str.append(TEXT.charAt(index));
				index = (index + 1) % LENGTH;
			}
		}
		Console.printLine(str.toString());
		Thread.sleep(MS_PER_TICK * 1);
		
		// Output the base of all lines spread into one
		str = new StringBuilder();
		str.append(StringUtils.repeat(' ', MARGIN_HALF));
		index = random.nextInt(LENGTH);
		for (int i = 0; i < WIDTH - MARGIN_HALF * 2; i++) {
			str.append(TEXT.charAt(index));
			index = (index + 1) % LENGTH;
		}
		Console.printLine(str.toString());
		
		// Spacing between base and giant HELLO WORLD
		Thread.sleep(MS_PER_TICK * 1);
		Console.printLine();
		Thread.sleep(MS_PER_TICK * 2);
		Console.printLine();
		
		
		// Output giant HELLO WORLD
		for (int i = 0; i < mask.length; i++) {
			Thread.sleep(MS_PER_TICK / 2);
			String line = mask[i];
			str = new StringBuilder();
			index = random.nextInt(LENGTH);
			for (int j = 0; j < line.length(); j++) {
				char c = line.charAt(j);
				if (c != ' ') {
					str.append(TEXT.charAt(index));
					index = (index + 1) % LENGTH;
				}
				else {
					str.append(' ');
				}
				if (str.length() == 14) {
					Console.print(str.toString());
					str = new StringBuilder();
					Thread.sleep(MS_PER_TICK / 2);
				}
			}
			Console.printLine(str.toString());
		}
		
		// Final spacing
		Thread.sleep(MS_PER_TICK * 3);
		Console.printLine();
		Thread.sleep(MS_PER_TICK * 4);
		Console.printLine();
		
		//OutputUtils.printLine("TODO: Hello World!", WIDTH, alignment);
		//OutputUtils.printLine("~~ A N I M A T E D ~~", WIDTH, alignment);
	}
	// </editor-fold>
}
