interface Stabel <T extends UtNorsk & OutEnglish> extends Iterable<T> {
	public void push(T t);
	public T top();
	public T pop();
}
