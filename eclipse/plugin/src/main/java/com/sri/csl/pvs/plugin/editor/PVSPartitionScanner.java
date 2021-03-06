package com.sri.csl.pvs.plugin.editor;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.jface.text.rules.EndOfLineRule;
import org.eclipse.jface.text.rules.IPredicateRule;
import org.eclipse.jface.text.rules.RuleBasedPartitionScanner;
import org.eclipse.jface.text.rules.SingleLineRule;
import org.eclipse.jface.text.rules.Token;

public class PVSPartitionScanner extends RuleBasedPartitionScanner {
	
	public final static String PVS_COMMENT = "__pvs_comment";

	public final static String PVS_TAG = "__pvs_tag";

	public final static String DEFINE_TAG = "__define_tag";

	public PVSPartitionScanner() {
		super();

		final List<SingleLineRule> rules = new ArrayList<SingleLineRule>();
		
		// Add rule for comments.
		rules.add(new EndOfLineRule(";", Token.UNDEFINED));
		rules.add(new EndOfLineRule(";;", Token.UNDEFINED));
		
		// Add rule for strings and character constants.
		rules.add(new SingleLineRule("\"", "\"", Token.UNDEFINED, '\\'));

		IPredicateRule[] result = new IPredicateRule[rules.size()];
		rules.toArray(result);
		setPredicateRules(result);
	}
}
