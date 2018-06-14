package edu.ricm3.game.tomatower.automaton;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import edu.ricm3.game.parser.Ast;
import edu.ricm3.game.parser.Ast.AI_Definitions;
import edu.ricm3.game.parser.Ast.Action;
import edu.ricm3.game.parser.Ast.Automaton;
import edu.ricm3.game.parser.Ast.Behaviour;
import edu.ricm3.game.parser.Ast.BinaryOp;
import edu.ricm3.game.parser.Ast.Condition;
import edu.ricm3.game.parser.Ast.Constant;
import edu.ricm3.game.parser.Ast.Direction;
import edu.ricm3.game.parser.Ast.Entity;
import edu.ricm3.game.parser.Ast.Expression;
import edu.ricm3.game.parser.Ast.FunCall;
import edu.ricm3.game.parser.Ast.Key;
import edu.ricm3.game.parser.Ast.Parameter;
import edu.ricm3.game.parser.Ast.Transition;
import edu.ricm3.game.parser.Ast.UnaryOp;
import edu.ricm3.game.parser.Ast.Value;
import edu.ricm3.game.tomatower.entities.enums.Kind;
import edu.ricm3.game.tomatower.mvc.Controller;

public class A_Builder {

	Ast ast;
	Controller c;

	public A_Builder(Ast a, Controller c) {
		this.ast = a;
		this.c = c;
	}

	public HashMap<String, A_Automaton> makeAutomatonsFromAst() throws Exception {

		HashMap<String, A_Automaton> res = new HashMap<>();

		for (Automaton a : ((AI_Definitions) ast).automata) {
			ArrayList<A_Behavior> behaviors = makeBehavior(a.behaviours);
			res.put(a.name.toString(), new A_Automaton(a.name.toString(), behaviors, a.entry.name.toString()));
		}

		return res;
	}

	private ArrayList<A_Behavior> makeBehavior(List<Behaviour> behaviors) throws Exception {
		ArrayList<A_Behavior> res = new ArrayList<>();
		for (Behaviour b_iter : behaviors) {
			ArrayList<A_Transition> transitions = makeTransitions(b_iter.transitions);
			A_Behavior behavior = new A_Behavior(b_iter.source.name.toString(), transitions);
			res.add(behavior);
		}
		return res;
	}

	private ArrayList<A_Transition> makeTransitions(List<Transition> transitions) throws Exception {
		ArrayList<A_Transition> res = new ArrayList<>();
		for (Transition t_iter : transitions) {
			A_Condition condition = makeCondition(t_iter.condition);
			A_Action action = makeAction(t_iter.action);
			A_Transition transition = new A_Transition(condition, action, t_iter.target.name.toString());
			res.add(transition);
		}
		return res;
	}

	private A_Condition makeCondition(Condition condition) throws Exception {
		// 3 cases : Binary Op, Unary Op or directly a funCall

		A_Expression expression;
		switch (condition.expression.kind) {
		case "FunCall":
			expression = makeFunCall((FunCall) condition.expression);
			break;

		case "BinaryOp":

			expression = makeBinaryOp((BinaryOp) condition.expression);
			break;

		case "UnaryOp":

			expression = makeUnaryOp((UnaryOp) condition.expression);
			break;

		default:
			throw new Exception("Illegal condition format");
		}

		return new A_Condition(expression);

	}

	private A_Action makeAction(Action action) throws Exception {
		// 2 cases : Binary Op (Or) or directyl a funcall

		A_Expression expression;
		switch (action.expression.kind) {
		case "FunCall":
			expression = makeFunCall((FunCall) action.expression);
			break;

		case "BinaryOp":
			expression = makeBinaryOp((BinaryOp) action.expression);
			break;

		default:
			throw new Exception("Illegal action format");
		}

		return new A_Action(expression);
	}

	public A_FunctionCall makeFunCall(FunCall funcall) throws Exception {

		ArrayList<A_Parameter> parameters = new ArrayList<>();
		for (Parameter parameter : funcall.parameters) {
			parameters.add(makeParameter(parameter));
		}
		return new A_FunctionCall(funcall.name.toString(), parameters, this.c);
	}

	private A_BinaryOp makeBinaryOp(BinaryOp binaryop) throws Exception {

		A_Expression e1 = makeExpression(binaryop.left_operand);
		A_Expression e2 = makeExpression(binaryop.right_operand);

		switch (binaryop.operator.toString()) {
		case "&":
			return new A_AndOp(e1, e2);

		case "/":
			return new A_OrOp(e1, e2);

		default:
			throw new Exception("Illegal binary operation");
		}

	}

	public A_UnaryOp makeUnaryOp(UnaryOp unaryop) throws Exception {
		A_Expression e = makeExpression(unaryop.operand);

		if (unaryop.operator.toString() == "!") {
			return new A_NotOp(e);
		} else {
			throw new Exception("Illegal operator : " + unaryop.toString());
		}
	}

	public A_Expression makeExpression(Expression expression) throws Exception {

		switch (expression.kind) {
		case "FunCall":
			return makeFunCall((FunCall) expression);

		case "BinaryOp":
			return makeBinaryOp((BinaryOp) expression);

		case "UnaryOp":
			return makeUnaryOp((UnaryOp) expression);

		default:
			throw new Exception("Illegal expression");
		}

	}

	public A_Parameter makeParameter(Parameter parameter) throws Exception {

		A_Parameter res;

		switch (parameter.kind) {
		case "Any":
			throw new Exception("TODO : Any dans makeParameter");
		case "Key":
			res = makeKeyParameter(((Key) parameter).value);
			break;

		case "Direction":
			res = makeDirectionParameter(((Direction) parameter).value);
			break;

		case "Entity":
			res = makeEntityParameter(((Entity) parameter).value);
			break;

		default:
			throw new Exception("Illegal parameters.");
		}
		return res;
	}

	public A_DirectionParameter makeDirectionParameter(Value value) throws Exception {

		A_DirectionParameter res;

		switch (value.kind) {

		case "Constant":
			res = new A_DirectionParameter(constantToDirection((Constant) value));
			break;
		case "Variable":
			res = new A_DirectionParameter(edu.ricm3.game.tomatower.entities.enums.Direction.FRONT);
		default:
			throw new Exception("Unknoww value type");
		}
		return res;
	}

	public A_EntityParameter makeEntityParameter(Value value) throws Exception {
		A_EntityParameter res;

		switch (value.kind) {

		case "Constant":
			res = new A_EntityParameter(constantToEntityKind((Constant) value));
			break;
		case "Variable":
			res = new A_EntityParameter(Kind.Obstacle);
		default:
			throw new Exception("Unknoww value type");
		}
		return res;
	}

	public A_KeyParameter makeKeyParameter(Value value) throws Exception {
		if (value.kind.equals("Constant"))
			return new A_KeyParameter(((Constant) value).value.value);
		else
			throw new Exception("Key parameter have to be a constant");
	}

	public edu.ricm3.game.tomatower.entities.enums.Direction constantToDirection(Constant c) {

		edu.ricm3.game.tomatower.entities.enums.Direction direction;

		switch (c.value.toString()) {
		case "N":
			direction = edu.ricm3.game.tomatower.entities.enums.Direction.NORTH;
			break;

		case "S":
			direction = edu.ricm3.game.tomatower.entities.enums.Direction.SOUTH;
			break;

		case "E":
			direction = edu.ricm3.game.tomatower.entities.enums.Direction.EAST;
			break;

		case "O":
			direction = edu.ricm3.game.tomatower.entities.enums.Direction.WEST;
			break;

		case "F":
			direction = edu.ricm3.game.tomatower.entities.enums.Direction.FRONT;
			break;
		case "B":
			direction = edu.ricm3.game.tomatower.entities.enums.Direction.BACK;
			break;

		case "L":
			direction = edu.ricm3.game.tomatower.entities.enums.Direction.ONTHELEFT;
			break;

		case "R":
			direction = edu.ricm3.game.tomatower.entities.enums.Direction.ONTHERIGHT;
			break;

		default:
			direction = edu.ricm3.game.tomatower.entities.enums.Direction.FRONT;
		}

		return direction;
	}

	public Kind constantToEntityKind(Constant c) {
		Kind kind;
		switch (c.value.toString()) {
		case "V":
			kind = Kind.Void;
			break;

		case "T":
			kind = Kind.Team;
			break;

		case "A":
			kind = Kind.Ennemis;
			break;

		case "P":
			kind = Kind.Takable;
			break;

		case "G":
			kind = Kind.Gate;
			break;
		case "D":
			kind = Kind.Danger;
			break;
		default:
			kind = Kind.Obstacle;
		}
		return kind;
	}

}