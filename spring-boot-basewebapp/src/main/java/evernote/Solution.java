// package evernote;



// import static org.junit.Assert.assertEquals;

// import java.util.Collection;
// import java.util.LinkedHashMap;
// import java.util.Map;
// import java.util.UUID;

// import org.junit.Before;
// import org.junit.jupiter.api.Test;
// import org.junit.runner.JUnitCore;




// /**
//  * @author franksun
//  * 
//  *         Mar 13, 2020
//  * 
//  */

// /**
//  * Provides a time value that can be forced to a value for testing.
//  */
// class CurrentTime {
// 	private static Long time;

// 	public static long get() {
// 		return time == null ? System.currentTimeMillis() : time;
// 	}

// 	public static void force(long time) {
// 		CurrentTime.time = time;
// 	}

// 	public static void unforce() {
// 		time = null;
// 	}
// }

// /**
//  * A note object that serves as the main unit of data for an Evernote user. All
//  * content that users add to Evernote must live in a Note. Notes are uniquely
//  * identified by their GUID.
//  */
// class Note {
// 	private String guid;
// 	private String name;
// 	private String content;
// 	private long created;

// 	public Note(String guid, String name, String content, long created) {
// 		this.guid = guid;
// 		this.name = name;
// 		this.content = content;
// 		this.created = created;
// 	}

// 	public String getGuid() {
// 		return guid;
// 	}

// 	public String getName() {
// 		return name;
// 	}

// 	public String getContent() {
// 		return content;
// 	}

// 	public long getCreated() {
// 		return created;
// 	}

// 	public void setName(String name) {
// 		this.name = name;
// 	}

// 	public void setContent(String content) {
// 		this.content = content;
// 	}
// }

// interface Command {
// 	void execute();
// }

// class Model {
// 	public enum SortCategory {
// 		NAME, CREATED
// 	}

// 	/** Notes indexed by their GUIDs. */
// 	private final Map<String, Note> notes = new LinkedHashMap<>();
// 	private final Command onUpdate;

// 	private SortCategory sortCategory = SortCategory.NAME;

// 	public Model(Command onUpdate) {
// 		this.onUpdate = onUpdate;
// 	}

// 	public Collection<Note> getAllNotes() {
// 		return notes.values();
// 	}

// 	public Note getNote(String guid) {
// 		return notes.get(guid);
// 	}

// 	public Note createNote(String name, String content) {
// 		Note note = new Note(UUID.randomUUID().toString(), name, content, CurrentTime.get());
// 		notes.put(note.getGuid(), note);
// 		onUpdate.execute();
// 		return note;
// 	}

// 	public void rename(String guid, String name) {
// 		notes.get(guid).setName(name);
// 		onUpdate.execute();
// 	}

// 	public void updateContent(String guid, String content) {
// 		notes.get(guid).setContent(content);
// 		onUpdate.execute();
// 	}

// 	public void sortByName() {
// 		sortCategory = SortCategory.NAME;
// 		onUpdate.execute();
// 	}

// 	public void sortByCreated() {
// 		sortCategory = SortCategory.CREATED;
// 		onUpdate.execute();
// 	}

// 	public SortCategory getSortCategory() {
// 		return sortCategory;
// 	}
// }

// class View {
// 	private final Model model;

// 	// HACK: In real life, we'd render to the UI. In this View, however, we "render"
// 	// to
// 	// a string variable. You can think of this as an intermediate step before
// 	// converting
// 	// the content to HTML (or something) and pushing to a graphical display.
// 	private String renderedContent;

// 	public View(Model model) {
// 		this.model = model;

// 		updateRenderedContent();
// 	}

// 	public void updateRenderedContent() {
// 		renderedContent = render();
// 	}

// 	public String getRenderedContent() {
// 		return renderedContent;
// 	}

// 	public String render() {
// 		StringBuilder sb = new StringBuilder();
// 		sb.append("START NOTE LIST\n");
// 		sb.append(renderNotes(model.getAllNotes()));
// 		sb.append("END NOTE LIST");
// 		return sb.toString();
// 	}

// 	private String renderNotes(Collection<Note> notes) {
// 		StringBuilder sb = new StringBuilder();
// 		sb.append("\nYou have ").append(notes.size()).append(" notes:\n");
// 		for (Note note : notes) {
// 			sb.append("Name: ").append(note.getName()).append('\n');
// 		}
// 		sb.append('\n');
// 		return sb.toString();
// 	}
// }

// class Controller {
// 	private final Model model;

// 	public Controller(Model model) {
// 		this.model = model;
// 	}

// 	public void createNote(String name, String content) {
// 		model.createNote(name, content);
// 	}

// 	/**
// 	 * Creates a note that shows a count of all previous notes that a user had
// 	 * created.
// 	 */
// 	public void createSummaryNote() {
// 		model.createNote("Summary", "Total notes: " + model.getAllNotes().size());
// 	}

// 	/**
// 	 * Takes two existing notes, and then creates a new note with their combined
// 	 * names and content.
// 	 */
// 	public void createMergedNote(String guid1, String guid2) {
// 		Note note1 = model.getNote(guid1);
// 		Note note2 = model.getNote(guid2);
// 		note1.setName(note1.getName() + " - " + note2.getName());
// 		note1.setContent(note1.getContent() + " - " + note2.getContent());
// 		model.createNote(note1.getName(), note1.getContent());
// 	}

// 	public void sortByName() {
// 		model.sortByName();
// 	}

// 	public void sortByCreated() {
// 		model.sortByCreated();
// 	}
// }

// class App {
// 	public final Model model;
// 	public final View view;
// 	public final Controller controller;

// 	public App() {
// 		model = new Model(new Command() {
// 			@Override
// 			public void execute() {
// 				view.updateRenderedContent();
// 			}
// 		});
// 		view = new View(model);
// 		controller = new Controller(model);
// 	}
// }

// public class Solution {
// 	public static void main(String[] args) {
// 		// Run all unit tests.
// 		JUnitCore.main("Solution");
// 	}

// 	@Before
// 	public void beforeTest() {
// 		CurrentTime.unforce();
// 	}

// 	@Test
// 	public void testModelRename() {
// 		App app = new App();
// 		String guid = app.model.createNote("name", "content").getGuid();
// 		app.model.rename(guid, "name2");
// 		assertEquals("name2", app.model.getNote(guid).getName());
// 	}

// 	@Test
// 	public void testModelUpdateContent() {
// 		App app = new App();
// 		String guid = app.model.createNote("name", "content").getGuid();
// 		app.model.updateContent(guid, "content2");
// 		assertEquals("content2", app.model.getNote(guid).getContent());
// 	}

// 	@Test
// 	public void testViewRender_shouldListAllNoteNamesInAlphabeticalOrder() {
// 		App app = new App();
// 		app.controller.createNote("alpha", "content");
// 		app.controller.createNote("charlie", "content");
// 		app.controller.createNote("bravo", "content");
// 		String expectedContent = new StringBuilder("START NOTE LIST\n").append("\nYou have 3 notes:\n")
// 				.append("Name: alpha\n").append("Name: bravo\n").append("Name: charlie\n").append("\nEND NOTE LIST")
// 				.toString();
// 		assertEquals(expectedContent, app.view.getRenderedContent());
// 	}

// 	@Test
// 	public void testViewRender_shouldListAllNotesInOrderOfCreation() {
// 		App app = new App();
// 		CurrentTime.force(1L);
// 		app.controller.createNote("alpha", "content");
// 		CurrentTime.force(2L);
// 		app.controller.createNote("charlie", "content");
// 		CurrentTime.force(3L);
// 		app.controller.createNote("bravo", "content");
// 		app.controller.sortByCreated();
// 		String expectedContent = new StringBuilder("START NOTE LIST\n").append("\nYou have 3 notes:\n")
// 				.append("Name: alpha\n").append("Name: charlie\n").append("Name: bravo\n").append("\nEND NOTE LIST")
// 				.toString();
// 		assertEquals(expectedContent, app.view.getRenderedContent());
// 	}

// 	@Test
// 	public void testViewRender_shouldRenderEmptyStateIfNoNotes() {
// 		App app = new App();
// 		String expectedContent = new StringBuilder("START NOTE LIST\n").append("No notes!").append("\nEND NOTE LIST")
// 				.toString();
// 		assertEquals(expectedContent, app.view.getRenderedContent());
// 	}

// 	@Test
// 	public void testControllerCreateSummaryNote_zeroNote() {
// 		App app = new App();
// 		app.controller.createSummaryNote();
// 		Note summaryNote = getNoteByName("Summary", app.model.getAllNotes());
// 		assertEquals("Total notes: 0", summaryNote.getContent());
// 	}

// 	@Test
// 	public void testControllerCreateSummaryNote_oneNote() {
// 		App app = new App();
// 		app.controller.createNote("alpha", "content");
// 		app.controller.createSummaryNote();
// 		Note summaryNote = getNoteByName("Summary", app.model.getAllNotes());
// 		assertEquals("Total notes: 1", summaryNote.getContent());
// 	}

// 	@Test
// 	public void testControllerCreateMergedNote_newNoteShouldBeCorrect() {
// 		App app = new App();
// 		String guid1 = app.model.createNote("name1", "one").getGuid();
// 		String guid2 = app.model.createNote("name2", "two").getGuid();
// 		app.controller.createMergedNote(guid1, guid2);
// 		Note mergedNote = getNoteByName("name1 - name2", app.model.getAllNotes());
// 		assertEquals("one - two", mergedNote.getContent());
// 	}

// 	@Test
// 	public void testControllerCreateMergedNote_existingNotesShouldNotBeModified() {
// 		App app = new App();
// 		String guid1 = app.model.createNote("name1", "one").getGuid();
// 		String guid2 = app.model.createNote("name2", "two").getGuid();
// 		app.controller.createMergedNote(guid1, guid2);
// 		assertEquals("name1", app.model.getNote(guid1).getName());
// 		assertEquals("one", app.model.getNote(guid1).getContent());
// 		assertEquals("name2", app.model.getNote(guid2).getName());
// 		assertEquals("two", app.model.getNote(guid2).getContent());
// 	}

// 	private Note getNoteByName(String name, Collection<Note> notes) {
// 		for (Note note : notes) {
// 			if (name.equals(note.getName())) {
// 				return note;
// 			}
// 		}
// 		return null;
// 	}
// }
