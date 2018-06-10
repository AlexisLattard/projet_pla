package edu.ricm3.game.tomatower.automaton;

import java.util.ArrayList;
import java.util.Iterator;
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


public class A_Builder {
	
	Ast ast;
	
	public A_Builder(Ast a) {
		this.ast = a;
	}
	
	
	
	public A_Automaton makeAutomatonFromAst() throws Exception {
		System.out.println("DEBUT MAKE AUTOMATON");
		
		System.out.println(ast.getClass());
		
		
		//Iterator<Automaton> iter_automatons = ((AI_Definitions)ast).getAutomatons().iterator();
		
		Automaton ast_automaton = ((AI_Definitions)ast).automata.get(0);
		ArrayList<A_Behavior> behaviors = makeBehavior(ast_automaton.behaviours);
		A_Automaton automaton = new A_Automaton(ast_automaton.name.toString(), behaviors,ast_automaton.entry.name.toString());
		
		if(behaviors == null)
			System.out.println("Bahaviors null");
		
		System.out.println("END MAKE AUTOMATON");
		return automaton;
	}
	
	
	private ArrayList<A_Behavior> makeBehavior(List<Behaviour> behaviors) throws Exception {
		ArrayList<A_Behavior> res = new ArrayList<>();
		for(Behaviour b_iter : behaviors) {
			ArrayList<A_Transition> transitions = makeTransitions(b_iter.transitions);
			A_Behavior behavior = new A_Behavior(b_iter.source.name.toString(), transitions);
			res.add(behavior);
		}
		return res;
	}
	
	
	private ArrayList<A_Transition> makeTransitions(List<Transition> transitions) throws Exception {
		ArrayList<A_Transition> res = new ArrayList<>();
		for(Transition t_iter : transitions) {
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
				expression = makeFunCall((FunCall)condition.expression);
				break;
			
			case "BinaryOp":
				expression = makeBinaryOp((BinaryOp)condition.expression);
				break;
			
			case "UnaryOp" :
				expression = makeBinaryOp((BinaryOp)condition.expression);
				break;
	
			default:
				throw new Exception("Illegal condition format");
		}
		
		return new A_Condition(expression);
		
	}
	
	
	private A_Action makeAction(Action action) throws Exception {
		// 2 cases : Binary Op (Or) or directyl a funcall
		
		
		A_Expression expression;
		switch(action.expression.kind) {
			case "FunCall":
				expression = makeFunCall((FunCall)action.expression);
				break;
				
			case "BinaryOp":
				expression = makeBinaryOp((BinaryOp)action.expression);
				break;
			
			default:
				throw new Exception("Illegal action format");
		}
		
		return new A_Action(expression);
	}
	

	public A_FunctionCall makeFunCall(FunCall funcall) throws Exception {

		ArrayList<A_Parameter> parameters = new ArrayList<>();
		for(Parameter parameter : funcall.parameters) {
			parameters.add(makeParameter(parameter));
		}
		return new A_FunctionCall(funcall.name.toString(), parameters);
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
		
		if(unaryop.operator.toString() == "!") {
			return new A_NotOp(e);
		}else {
			throw new Exception("Illegal operator : " + unaryop.toString());
		}
	}
	
	
	public A_Expression makeExpression(Expression expression) throws Exception {
		
		switch (expression.kind) {
			case "FunCall":
				return makeFunCall((FunCall)expression);
				
			case "BinaryOp":
				return makeBinaryOp((BinaryOp)expression);
				
			case "UnaryOp" :
				return makeUnaryOp((UnaryOp)expression);
				
			default:
				throw new Exception("Illegal expression");
		}
		
	}
	
	
	public A_Parameter makeParameter(Parameter parameter) throws Exception {
		System.out.println("parameter");
		
		A_Parameter res;
		
		switch (parameter.kind) {
			case "Any":
				System.out.println("TODO : parameter any");
				res = null;
				break;
			case "Key":
				res = makeKeyParameter(((Key)parameter).value);
				break;
				
			case "Direction":
				res = makeDirectionParameter(((Direction)parameter).value);
				break;
			
			case "Entity":
				res = makeEntityParameter(((Entity)parameter).value);
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
				res = new A_DirectionParameter(constantToDirection((Constant)value));
				break;
			case "Variable":
				throw new Exception("TODO : variable not supported");
			default:
				throw new Exception("Unknoww value type");
		}
		return res;
	}
	
	
	public A_EntityParameter makeEntityParameter(Value value) throws Exception {
		A_EntityParameter res;
		
		switch (value.kind) {
			case "Constant":
				res = new A_EntityParameter(constantToEntityKind((Constant)value));
				break;
			case "Variable":
				throw new Exception("TODO : variable not supported");
			default:
				throw new Exception("Unknoww value type");
		}
		return res;
	}
	
	
	public A_EntityParameter makeKeyParameter(Value value) {
		return null;
	}
	
	
	public edu.ricm3.game.tomatower.entities.enums.Direction constantToDirection(Constant c) {
		
		edu.ricm3.game.tomatower.entities.enums.Direction direction;
		
		switch (c.value.toString()) {
			case "N":
				direction = edu.ricm3.game.tomatower.entities.enums.Direction.UP;
				break;
				
			case "S":
				direction = edu.ricm3.game.tomatower.entities.enums.Direction.DOWN;
				break;
				
			case "E":
				direction = edu.ricm3.game.tomatower.entities.enums.Direction.RIGHT;
				break;
				
			case "O":
				direction = edu.ricm3.game.tomatower.entities.enums.Direction.LEFT;
				break;
				
			case "F":
				direction = edu.ricm3.game.tomatower.entities.enums.Direction.UP;
				break;
			case "B":
				direction = edu.ricm3.game.tomatower.entities.enums.Direction.UP;
				break;
				
			case "L":
				direction = edu.ricm3.game.tomatower.entities.enums.Direction.UP;
				break;
				
			case "R":
				direction = edu.ricm3.game.tomatower.entities.enums.Direction.UP;
				break;
				
			default:
				direction = edu.ricm3.game.tomatower.entities.enums.Direction.UP;
		}
		
		return direction;
	}
	
	public Kind constantToEntityKind(Constant c) {
		Kind kind;
		switch (c.value.toString()) {
			case "V":
				kind = Kind.Obstacle;
				break;
				
			case "T":
				kind = Kind.Team;
				break;
				
			case "A":
				kind = Kind.Ennemis;
				break;
				
			case "P":
				kind = Kind.Unknwon;
				break;
				
			case "G":
				kind = Kind.Gate;
				break;
				
			default:
				kind = Kind.Unknwon;
		}
		return kind;
	}
	
	
	
	

	
	
	
	
	
}